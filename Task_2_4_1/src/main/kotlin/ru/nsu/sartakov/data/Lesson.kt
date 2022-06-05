package ru.nsu.sartakov.data

import java.time.LocalDateTime

data class Lesson(
    var date: LocalDateTime,
    var attendance: Boolean
)
