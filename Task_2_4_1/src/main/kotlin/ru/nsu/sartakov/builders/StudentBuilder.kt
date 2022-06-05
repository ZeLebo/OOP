package ru.nsu.sartakov.builders

import ru.nsu.sartakov.entities.GivenTask
import ru.nsu.sartakov.entities.Student

class StudentBuilder(nickName: String) {
    var nickName = ""
    var firstName = ""
    var lastName = ""
    var Url = ""

    var givenTasks = mutableListOf<GivenTask>()

    fun build(): Student {
        return Student(nickName, firstName, lastName, Url, givenTasks)
    }

    fun init() {
        TODO("not implemented");
    }
}