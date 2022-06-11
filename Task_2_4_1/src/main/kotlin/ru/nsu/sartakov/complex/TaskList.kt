package ru.nsu.sartakov.complex

import ru.nsu.sartakov.entities.Task

data class TaskList(
    var tasks: MutableList<Task>
)
