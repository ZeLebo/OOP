package ru.nsu.sartakov.builders

import ru.nsu.sartakov.entities.Group

class GroupBuilder {
    var number: Int = 0

    fun build(): Group {
        if (number == 0) {
            throw IllegalStateException("Group number is not set")
        }
        return Group(number)
    }
}