package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.builders.StudentBuilder
import ru.nsu.sartakov.builders.TaskBuilder
import ru.nsu.sartakov.entities.Group

class DSL {
    fun student(block: StudentBuilder.() -> Unit) {
        StudentBuilder().apply(block).build()
    }

    val student = student {
        nickName = "ZeLebo"
        firstName = "Alexander"
        lastName = "Sartakov"
        url = "https://github.com/ZeLebo"
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

    fun taskList(block: TaskBuilder.() -> Unit) {
        TaskBuilder().apply(block).build()
    }

    val tasks = taskList {
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
        group.addStudent(studentTest)
        group.addStudent(studentTest)

        for (student in group.students) {
            println(student.nickName)
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
            return
        } else if (args[0] == "build") {
            println("Building")
            return
        } else {
            println("Unknown command line arg")
            return
        }
    }
}