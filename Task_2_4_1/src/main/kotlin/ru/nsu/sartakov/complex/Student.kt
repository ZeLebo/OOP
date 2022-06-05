package ru.nsu.sartakov.complex

import ru.nsu.sartakov.entities.Group
import ru.nsu.sartakov.entities.Lesson
import ru.nsu.sartakov.entities.Mark
import ru.nsu.sartakov.entities.Task

data class Student(
    var nickName: String,
    var firstName: String,
    var lastName: String,
    var url: String,

    // complex things
    var group: Group,
    var givenTasks: List<Task>,
    val lessons: List<Lesson>,
    val marks: List<Mark>,
)
