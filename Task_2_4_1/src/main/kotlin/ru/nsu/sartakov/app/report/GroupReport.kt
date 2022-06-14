package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Group

class GroupReport(group: Group) {
    val group = group
    val students = group.students

    fun printFullReportTerminal() {
        println("Group: ${group.number}")
        println("Students:")
        for (student in students) {
            println("${student.firstName} ${student.lastName} is ${student.nickName}")
        }
        println()

        for (student in students) {
            StudentReport(student).printReportTerminal()
        }
    }

    fun printTaskReportTerminal(task: String) {
        println("Group: ${group.number}")
        for (student in students) {
            if (task in student.givenTasks) {
                println(student.nickName)
                println("Attendance: ${student.attendance()}")
                if (student.marks.isNotEmpty()) {
                    println("Marks:")
                    for (mark in student.marks) {
                        println("\t${mark.value} on ${mark.date}")
                    }
                }
                println("")
                TaskReport(student, task).printReportTerminal()
            }
        }
    }
}