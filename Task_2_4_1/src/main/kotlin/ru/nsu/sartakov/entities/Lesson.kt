package ru.nsu.sartakov.entities

import java.time.LocalDateTime

data class Lesson (
    // this is supposed to name "Class", but that's a problem…
    var date: LocalDateTime,
    var attendance: Boolean
)
