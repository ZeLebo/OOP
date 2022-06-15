package ru.nsu.sartakov.complex

import ru.nsu.sartakov.builders.GroupBuilder
import ru.nsu.sartakov.entities.Group
import ru.nsu.sartakov.entities.Student

class Groups : ArrayList<Group>() {
    fun group(block: GroupBuilder.() -> Unit) =
        add(GroupBuilder().apply(block).build())

    // get the group by number
    fun getGroup(number: Int): Group? {
        for (group in this) {
            if (group.number == number) {
                return group
            }
        }
        return null
    }

    fun isGroup(number: Int): Boolean {
        for (group in this) {
            if (group.number == number) {
                return true
            }
        }
        return false
    }

    fun getStudent(nick : String) : Student? {
        for (group in this) {
            for (student in group.students) {
                if (student.nickName == nick) {
                    return student
                }
            }
        }
        return null
    }

    fun addGroup(number: Int) {
        this.add(Group(number))
    }
}