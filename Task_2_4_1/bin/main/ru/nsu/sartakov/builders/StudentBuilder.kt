package ru.nsu.sartakov.builders

import ru.nsu.sartakov.complex.Lessons
import ru.nsu.sartakov.complex.Marks
import ru.nsu.sartakov.entities.Student
import ru.nsu.sartakov.complex.Tasks
import ru.nsu.sartakov.entities.*

class StudentBuilder() {
    var nickName = ""
    var firstName = ""
    var lastName = ""
    var url = ""
    var group : Int = 0



    var givenTasks = mutableListOf<String>()
    var marks = mutableListOf<Mark>()

    fun tasks(givenTasks: List<String>) {
        this.givenTasks.addAll(givenTasks)
    }

    // fun taking many args of string
    fun givenTasks(vararg givenTasks: String) {
        this.givenTasks.addAll(givenTasks)
    }

    fun marks(block: Marks.() -> Unit) =
        marks.addAll(Marks().apply(block))

    fun build(): Student =
        Student(nickName, firstName, lastName, url, group, givenTasks, marks)
}