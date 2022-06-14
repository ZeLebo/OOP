package ru.nsu.sartakov.entities

import ru.nsu.sartakov.app.report.GitRunner

data class Student(
    var nickName: String,
    var firstName: String,
    var lastName: String,
    var url: String,
    var group: Int,
    var givenTasks: List<String>,
    val marks: List<Mark>,
) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }

    fun addTask(task: Task) {
        givenTasks.add(task.taskId)
    }

    fun attendance() = GitRunner().checkAttendance(this)
}

private fun <E> List<E>.add(taskId: String) {
    add(taskId)
}
