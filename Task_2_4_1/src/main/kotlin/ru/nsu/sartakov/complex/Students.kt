package ru.nsu.sartakov.complex

import ru.nsu.sartakov.builders.StudentBuilder
import ru.nsu.sartakov.entities.Student

class Students : ArrayList<Student>() {
    fun students(block: StudentBuilder.() -> Unit) =
        add(StudentBuilder().apply(block).build())
}