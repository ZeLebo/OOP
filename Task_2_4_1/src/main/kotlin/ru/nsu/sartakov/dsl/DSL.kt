package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.builders.StudentBuilder
import ru.nsu.sartakov.builders.TaskBuilder

class DSL {
    fun student(block: StudentBuilder.() -> Unit) {
        StudentBuilder().apply(block).build()
    }

    fun taskList(block: TaskBuilder.() -> Unit) {
        TaskBuilder().apply(block).build()
    }

    val student = student {
        nickName = "ZeLebo"
        firstName = "Alexander"
        lastName = "Sartakov"
        url = "https://github.com/ZeLebo"

        group {
            number = 20214
        }

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

    val tasks = taskList {
        task()
        task()
    }
}

fun main() {
    val dsl = DSL()
    println(dsl.student)
    println(dsl.tasks)
}