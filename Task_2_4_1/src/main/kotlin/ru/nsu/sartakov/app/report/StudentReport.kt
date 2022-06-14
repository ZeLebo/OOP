package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student

class StudentReport(val student: Student) {
    private val gitRunner = GitRunner()
    private val attendance = gitRunner.checkAttendance(student)
    private val tasksReport = ArrayList<TaskReport>()
    private val marks = student.marks

    private fun generateTasksReport() {
        for (task in student.givenTasks) {
            tasksReport.add(TaskReport(student, task))
        }
    }

    fun printReportTerminal() {
        generateTasksReport()
        println("Student: ${student.nickName}")
        println("Attendance: $attendance")
        println()
        for (taskReport in tasksReport) {
            taskReport.printReportTerminal()
        }
        for (mark in marks) {
            println("Mark: ${mark.value} of ${mark.date}")
        }
        println()
    }

    fun saveReport() {
        generateTasksReport()
        TODO("save to html file")
    }
}