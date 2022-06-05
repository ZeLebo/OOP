package ru.nsu.sartakov.data

import java.time.LocalDateTime

data class GivenTask(
    var taskId: String,
    var deadline: LocalDateTime
)
