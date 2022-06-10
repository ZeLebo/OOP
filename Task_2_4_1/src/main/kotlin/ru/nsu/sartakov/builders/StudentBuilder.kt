package ru.nsu.sartakov.builders

import ru.nsu.sartakov.complex.Lessons
import ru.nsu.sartakov.complex.Marks
import ru.nsu.sartakov.complex.Student
import ru.nsu.sartakov.complex.Tasks
import ru.nsu.sartakov.entities.*

class StudentBuilder() {
    var nickName = ""
    var firstName = ""
    var lastName = ""
    var url = ""
    var group : Int = 0

    var givenTasks = mutableListOf<Task>()
    var lessons = mutableListOf<Lesson>()
    var marks = mutableListOf<Mark>()

    fun tasks(block: Tasks.() -> Unit) =
        givenTasks.addAll(Tasks().apply(block))

    fun lessons(block: Lessons.() -> Unit) =
        lessons.addAll(Lessons().apply(block))

    fun marks(block: Marks.() -> Unit) =
        marks.addAll(Marks().apply(block))

    fun build(): Student =
        Student(nickName, firstName, lastName, url, group, givenTasks, lessons, marks)
}