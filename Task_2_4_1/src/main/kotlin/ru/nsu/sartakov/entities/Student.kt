package ru.nsu.sartakov.entities

import ru.nsu.sartakov.entities.Lesson
import ru.nsu.sartakov.entities.Mark
import ru.nsu.sartakov.entities.Task

data class Student(
    var nickName: String,
    var firstName: String,
    var lastName: String,
    var url: String,
    var group: Int,
    var givenTasks: List<Task>,
    val lessons: List<Lesson>,
    val marks: List<Mark>,
) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}
