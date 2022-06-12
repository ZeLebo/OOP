package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL


/**
 * Class for running tests and stuff like this
 * All the args are gere
 */

class App {
    fun checkTask(taskName: String) : Boolean {
        return DSL().tasks.any { it.taskId == taskName }
    }


    fun runTasks(nickname: String) {
        val student = DSL().groups.getStudent(nickname)
        if (student == null) {
            println("Student $nickname not found")
            return
        }
        student.givenTasks.forEach { task -> runTask(nickname, task) }
    }

    fun runTasks(groupNumber: Int) {
        val group = DSL().groups.getGroup(groupNumber)
        if (group == null) {
            println("Group $groupNumber not found")
            return
        }
        group.students.forEach { student ->
            student.givenTasks.forEach { task -> runTask(student.nickName, task) }
        }

    }

    fun runTask(nickname: String, task: String) {
        val student = DSL().groups.getStudent(nickname)
        if (student == null) {
            println("Student $nickname not found")
            return
        }
        GitRunner().runTests(student, task)
    }

    fun runTask(group: Int, task: String) {
        when {
            !DSL().groups.isGroup(group) -> {
                println("Group $group does not exist")
                return
            }
            !checkTask(task) -> {
                println("Task $task does not exist")
                return
            }
        }
        // run tests for all the students in the group
        DSL().groups.getGroup(group)?.students?.forEach { student ->
            runTask(student.nickName, task)
        } ?: println("Group $group does not exist")
    }

    fun generateDocs(nickname: String, task: String) {
        val student = DSL().groups.getStudent(nickname)
        if (student == null) {
            println("Student $nickname not found")
            return
        }
        GitRunner().generateDocs(student, task)
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No command line args")
        return
    }
    // app student nickname run task
    // app student nickname docs task
    // app student nickname run all
    // app group groupNumber run task
    // app group groupNumber run all
    if (args.size < 4) {
        println("Not enough args")
        return
    }

    when {
        args[0] == "student" -> {
            if (args[3] == "run") {
                if (args[4] == "all") {
                    println("Running all tasks for student ${args[1]}")
                    App().runTasks(args[1])
                } else {
                    println("Running task ${args[4]}")
                    App().runTask(args[1], args[4])
                }
            } else if (args[3] == "docs") {
                println("Generating docs for task ${args[4]}")
                App().generateDocs(args[1], args[4])

            } else {
                println("Unknown command")
            }
        }
        args[0] == "group" -> {
            if (args[3] == "run") {
                if (args[4] == "all") {
                    println("Running all tasks for group ${args[2]}")
                    App().runTasks(args[2].toInt())
                } else {
                    println("Running task ${args[4]}")
                    App().runTask(args[2].toInt(), args[4])
                }
            } else {
                println("Unknown command")
            }
        }
        else -> {
            println("Unknown command")
        }
    }
}