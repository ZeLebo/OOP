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


    lateinit var group: Group
    fun group(init: GroupBuilder.() -> Unit) {
        group = GroupBuilder().apply(init).build()
    }

    var givenTasks = mutableListOf<Task>()
    fun tasks(block: Tasks.() -> Unit) {
        givenTasks.addAll(Tasks().apply(block))
    }

    var lessons = mutableListOf<Lesson>()
    fun lessons(block: Lessons.() -> Unit) {
        lessons.addAll(Lessons().apply(block))
    }

    var marks = mutableListOf<Mark>()
    fun marks(block: Marks.() -> Unit) {
        marks.addAll(Marks().apply(block))
    }

    fun build(): Student {
        return Student(nickName, firstName, lastName, url, group, givenTasks, lessons, marks)
    }


    fun init() {
        TODO("not implemented");
    }
}