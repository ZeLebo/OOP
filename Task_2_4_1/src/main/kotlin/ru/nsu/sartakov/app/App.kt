package ru.nsu.sartakov.app

import ru.nsu.sartakov.app.calculate.GitRunner
import ru.nsu.sartakov.dsl.DSL


/**
 * Class for running tests and stuff like this
 * All the args are gere
 */

class App {
    private fun checkTask(taskName: String) : Boolean {
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
        val result = GitRunner().runTests(student, task)
        if (result.first) {
            println("Student $nickname passed build of task $task")
            if (result.second) {
                println("Student $nickname passed tests for task $task")
            } else {
                println("Student $nickname failed tests for task $task")
            }
        } else {
            println("Student $nickname failed build of task $task")
        }
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
    // app student nickname task
    // app student nickname all
    // app group groupNumber task
    // app group groupNumber all
    if (args.size < 3) {
        println("Not enough args")
        return
    }

    when {
        args[0] == "student" -> {
            if (args[2] == "all") {
                println("Running all tasks for ${args[1]}")
                App().runTasks(args[1])
            } else {
                println("Running task ${args[2]} for ${args[1]}")
                App().runTask(args[1], args[2])
            }
        }
        args[0] == "group" -> {
            if (args[2] == "all") {
                println("Running all tasks for ${args[1]}")
                App().runTasks(args[1].toInt())
            } else {
                println("Running task ${args[2]} for ${args[1]}")
                App().runTask(args[1].toInt(), args[2])
            }
        }
        args[0] == "docs" -> {
            App().generateDocs(args[1], args[2])
        }
        else -> {
            println("Unknown command")
        }
    }
}