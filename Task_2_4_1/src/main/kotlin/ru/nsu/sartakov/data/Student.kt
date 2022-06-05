package ru.nsu.sartakov.data

data class Student(
    var nickName: String,
    var firstName: String,
    var lastName: String,
    var group: String,
    var tasks: List<GivenTask>,
    val lessons: List<Lesson>,
    val marks: List<Mark>
)
