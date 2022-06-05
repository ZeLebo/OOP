package ru.nsu.sartakov.builders

import ru.nsu.sartakov.complex.TaskList
import ru.nsu.sartakov.entities.Task
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskBuilder {
    var tasks = mutableListOf<Task>()

    fun tasks(block: MutableList<Task>.() -> Unit) {
        tasks.block()
    }

    var taskId = ""

    private val dateFormatter : DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    var deadLine: String = (LocalDate.parse("01.01.2000", dateFormatter)).toString()

    var date: String = ""

    var score = -1

    fun task() {
        val task = Task(taskId, score)
        tasks.add(task)
    }

    fun buildTask(): Task {
        if (taskId.isEmpty()) {
            throw IllegalArgumentException("Task id is empty")
        }

        if (score < 0) {
            throw IllegalArgumentException("Task score is negative")
        }

        return Task(taskId, score)
    }

    fun build(): TaskList {
        return TaskList(tasks)
    }

}