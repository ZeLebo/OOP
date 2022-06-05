package ru.nsu.sartakov.entities

import java.time.LocalDate


data class Lesson (
    // this is supposed to name "Class", but that's a problem…
    var date: LocalDate,
    var attendance: Boolean
)
