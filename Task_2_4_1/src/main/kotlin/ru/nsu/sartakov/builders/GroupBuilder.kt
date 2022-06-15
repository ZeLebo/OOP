package ru.nsu.sartakov.builders

import ru.nsu.sartakov.complex.Students
import ru.nsu.sartakov.entities.Student
import ru.nsu.sartakov.entities.Group

class GroupBuilder {
    var number: Int = 0
    var students: MutableList<Student> = mutableListOf()

    fun students(block: Students.() -> Unit) {
        students.addAll(Students().apply(block))
    }

    fun build(): Group {
        if (number == 0) {
            throw IllegalStateException("Group number is not set")
        }
        return Group(number, students)
    }
}