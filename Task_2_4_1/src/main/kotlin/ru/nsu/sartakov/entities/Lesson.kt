package ru.nsu.sartakov.entities

import java.time.LocalDateTime

data class Lesson(
    var date: LocalDateTime,
    var attendance: Boolean
)
