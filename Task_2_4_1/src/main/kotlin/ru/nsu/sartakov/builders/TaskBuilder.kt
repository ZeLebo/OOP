package ru.nsu.sartakov.builders

import ru.nsu.sartakov.complex.TaskList
import ru.nsu.sartakov.entities.Task

class TaskBuilder {
    var tasks = mutableListOf<Task>()

    fun tasks(block: MutableList<Task>.() -> Unit) {
        tasks.block()
    }

    var taskId = ""
    var score = -1

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