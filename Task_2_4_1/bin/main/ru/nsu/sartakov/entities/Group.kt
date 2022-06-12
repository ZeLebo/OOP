package ru.nsu.sartakov.entities

class Group (
    var number: Int,
    var students: MutableList<Student> = ArrayList()){

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