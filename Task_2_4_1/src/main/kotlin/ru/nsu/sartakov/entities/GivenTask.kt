package ru.nsu.sartakov.entities

import java.time.LocalDateTime

data class GivenTask(
    var taskId: String,
    var deadline: LocalDateTime
)
