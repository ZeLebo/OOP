package ru.nsu.sartakov.entities

data class Student(
    var nickName: String,
    var firstName: String,
    var lastName: String,
    var url: String,

    // complex things
    var group: MutableList<GivenTask>,
    var givenTasks: List<GivenTask>,
    val lessons: List<Lesson>,
    val marks: List<Mark>
)
