package ru.nsu.sartakov.app

import ru.nsu.sartakov.app.report.GroupReport
import ru.nsu.sartakov.app.report.StudentReport
import ru.nsu.sartakov.app.report.TaskReport
import ru.nsu.sartakov.dsl.DSL


/**
 * Class for running tests and stuff like this
 * All the args are gere
 */

class App {
    private fun checkTask(taskName: String) : Boolean {
        return DSL().tasks().any { it.taskId == taskName }
    }

    private fun openReport() {
        // ask user if he wants to open the file
        println("Do you want to open the report?[y/n]")
        val answer = readLine()
        if (answer == "y") {
            Runtime.getRuntime().exec("open ${DSL().fileFinder("report.html")}").waitFor()
        }
    }

    fun runTasks(nickname: String) {
        val student = DSL().groups().getStudent(nickname)
        if (student == null) {
            println("Student $nickname not found")
            return
        }
        val report = StudentReport(student)
        report.printReportTerminal()
        report.saveReport()
        openReport()
    }

    fun runTasks(groupNumber: Int) {
        val group = DSL().groups().getGroup(groupNumber)
        if (group == null) {
            println("Group $groupNumber not found")
            return
        }
        val report = GroupReport(group)
        report.printFullReportTerminal()
        report.saveFullReport()
        openReport()
    }

    fun runTask(nickname: String, task: String) {
        val student = DSL().groups().getStudent(nickname)
        if (student == null) {
            println("Student $nickname not found")
            return
        }
        if (task !in student.givenTasks) {
            println("Task $task not found in given tasks for this student")
            return
        }
        // print to terminal
        val report = TaskReport(student, task)
        report.printReportTerminal()
        report.saveReport()
        openReport()
    }

    fun runTask(group: Int, task: String) {
        val group = DSL().groups().getGroup(group)

        if (group == null) {
            println("Group $group does not exist")
            return
        }
        else if (!checkTask(task)) {
            println("Task $task does not exist")
            return
        }
        val report = GroupReport(group)
        report.printTaskReportTerminal(task)
        report.saveTaskReport(task)
        openReport()
    }
}

fun main(args: Array<String>) {
    if ("help" in args) println(
        """
        Usage:
        app.jar [nickname] [task] - to run task for student
        app.jar [nickname] all - to run all tasks for student
        app.jar [group] [task] - run task for all students (in they have it) in group
        app.jar [group] all - run all tasks for all students in group
        """
    )

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
        else -> {
            println("Unknown command")
        }
    }
}