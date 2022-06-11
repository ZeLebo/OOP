package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.builders.StudentBuilder
import ru.nsu.sartakov.complex.Student
import ru.nsu.sartakov.complex.Tasks
import ru.nsu.sartakov.entities.Group

class DSL {
    private fun student(block: StudentBuilder.() -> Unit): Student {
        return StudentBuilder().apply(block).build()
    }

    val student = student {
        nickName = "ZeLebo"
        firstName = "Alexander"
        lastName = "Sartakov"
        url = "https://github.com/ZeLebo/OOP"
        group = 20214

        tasks {
            task {
                taskId = "Task_1_1_1"
                deadLine = "01.01.2022"
                score = 5
            }
            task {
                taskId = "Task_1_1_2"
                score = 5
                deadLine = "01.01.2022"
            }
        }
        lessons {
            lesson {
                date = "01.01.2022"
                attendance = true
            }
            lesson {
                date = "01.01.2023"
                attendance = false
            }
        }
        marks {
            mark {
                value = 5
                date = "01.01.2022"
            }
            mark {
                value = 4
                date = "01.01.2022"
            }
        }
    }

    private fun tasks(block: Tasks.() -> Unit): Tasks {
        return Tasks().apply(block)
    }

    val tasks = tasks{
        task {
            taskId = "Task_1_1_1"
            deadLine = "01.01.2022"
            score = 5
        }
        task {
            taskId = "Task_1_1_2"
            score = 5
            deadLine = "01.01.2022"
        }
    }

    fun test() {
        val group = Group(20214)
        val studentTest = StudentBuilder().apply {
            nickName = "ZeLebo"
            firstName = "Alexander"
            lastName = "Sartakov"
            url = ""
        }.build()

        group.addStudent(studentTest)

        // for each mark in student mark print
        student.marks.forEach {
            println(it.toString())
        }

        println("Testing taskList")
        for (task in tasks) {
            println(task.toString())
        }
    }
}

fun main(args: Array<String>) {
    // check for command line args
    if (args.isEmpty()) {
        println("No command line args")
    } else {
        // if arg is "test" run test
        if (args[0] == "test") {
            DSL().test()
            val dsl = DSL()
            dsl.tasks.forEach { println(it.toString()) }
            // print all student info
            println(dsl.student.toString())
        } else if (args[0] == "build") {
            println("Building")
            return
        } else {
            println("Unknown command line arg")
            return
        }
    }
}