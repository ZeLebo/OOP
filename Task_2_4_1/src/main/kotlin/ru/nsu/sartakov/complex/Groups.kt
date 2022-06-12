package ru.nsu.sartakov.complex

import ru.nsu.sartakov.builders.GroupBuilder
import ru.nsu.sartakov.entities.Group

class Groups : ArrayList<Group>() {
    fun group(block: GroupBuilder.() -> Unit) =
        add(GroupBuilder().apply(block).build())

    // get the group by number
    fun getGroup(number: Int): Group {
        for (group in this) {
            if (group.number == number) {
                return group
            }
        }
        throw IllegalArgumentException("Group with number $number not found")
    }

    fun isGroup(number: Int): Boolean {
        for (group in this) {
            if (group.number == number) {
                return true
            }
        }
        return false
    }
}