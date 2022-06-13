package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student

class StudentReport(student : Student) {
    val gitRunner = GitRunner()
    val attendance = gitRunner.checkAttendance(student)
    val tasksReport = ArrayList<TaskReport>()

    fun generateTasksReport(student : Student) {
        for (task in student.givenTasks) {
            tasksReport.add(TaskReport(student, task))
        }
    }
}