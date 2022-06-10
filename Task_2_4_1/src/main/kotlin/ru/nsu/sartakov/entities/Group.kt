package ru.nsu.sartakov.entities

import ru.nsu.sartakov.complex.Student

class Group
    (var number: Int) {
    var students : ArrayList<Student> = ArrayList()

    fun addStudent(student: Student) {
        students.add(student)
        student.group = this.number
    }

    fun removeStudent(student: Student) {
        students.remove(student)
        student.group = 0
    }

    fun getStudent(index: Int): Student {
        return students[index]
    }
}