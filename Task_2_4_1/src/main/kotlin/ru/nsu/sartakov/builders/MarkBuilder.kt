package ru.nsu.sartakov.builders

import ru.nsu.sartakov.entities.Mark
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MarkBuilder {
    var value: Int = 0
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private var markDate: LocalDate = LocalDate.parse("01.01.2019", dateTimeFormatter)

    var date: String = ""
    set(value) {
            markDate = LocalDate.parse(value, dateTimeFormatter)
    }

    fun build(): Mark {
        if (value == 0) {
            throw IllegalArgumentException("Value cannot be 0")
        }
        if (date.isEmpty()) {
            throw IllegalArgumentException("Date cannot be empty")
        }
        if (markDate == LocalDate.parse("01.01.2019", dateTimeFormatter)) {
            throw IllegalArgumentException("Need to set date after 01.01.2019\nJust I want that")
        }
        return Mark(value, markDate)
    }
}