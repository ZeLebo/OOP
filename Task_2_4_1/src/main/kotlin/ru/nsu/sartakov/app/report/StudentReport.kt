package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student

class StudentReport(student : Student) {
    val student = student
    val gitRunner = GitRunner()
    val attendance = gitRunner.checkAttendance(student)
    val tasksReport = ArrayList<TaskReport>()
    val marks = student.marks

    fun generateTasksReport() {
        for (task in student.givenTasks) {
            tasksReport.add(TaskReport(student, task))
        }
    }

    fun printReportTerminal() {
        generateTasksReport()
        println("Student: ${student.nickName}")
        println("Attendance: ${attendance}")
        println()
        for (taskReport in tasksReport) {
            taskReport.printReportTerminal()
        }
        for (mark in marks) {
            println("Mark: ${mark.value} of ${mark.date}")
        }
        println()
    }
}