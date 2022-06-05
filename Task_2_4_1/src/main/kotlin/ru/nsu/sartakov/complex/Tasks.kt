package ru.nsu.sartakov.complex

import ru.nsu.sartakov.builders.TaskBuilder
import ru.nsu.sartakov.entities.Task

class Tasks: ArrayList<Task>() {
        fun task(block: TaskBuilder.() -> Unit) {
            add(TaskBuilder().apply(block).buildTask())
        }
}