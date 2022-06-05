package ru.nsu.sartakov.builders

import ru.nsu.sartakov.complex.Student
import ru.nsu.sartakov.entities.*

class StudentBuilder() {
    var nickName = ""
    var firstName = ""
    var lastName = ""

    var url = ""

    lateinit var group: Group
    var givenTasks = mutableListOf<Task>()
    var lessons = mutableListOf<Lesson>()
    var marks = mutableListOf<Mark>()

    // init the vars

    fun marks(init: MutableList<Mark>.() -> Unit) {
        marks = mutableListOf<Mark>().apply(init)
    }

    fun lessons(init: MutableList<Lesson>.() -> Unit) {
        lessons = mutableListOf<Lesson>().apply(init)
    }

    fun givenTasks(init: MutableList<Task>.() -> Unit) {
        givenTasks = mutableListOf<Task>().apply(init)
    }


    fun build(): Student {
        return Student(nickName, firstName, lastName, url, group, givenTasks, lessons, marks)
    }


    fun init() {
        TODO("not implemented");
    }
}