package ru.nsu.sartakov.builders

import ru.nsu.sartakov.entities.Task
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskBuilder {
    var taskId = ""
    var score = -1

    private val dateFormatter : DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    var deadLine: String = (LocalDate.parse("01.01.2000", dateFormatter)).toString()

    fun build(): Task {
        if (taskId.isEmpty()) {
            throw IllegalArgumentException("Task id is empty")
        }

        if (score < 0) {
            throw IllegalArgumentException("Task score is negative")
        }

        return Task(taskId, score, deadLine)
    }
}
