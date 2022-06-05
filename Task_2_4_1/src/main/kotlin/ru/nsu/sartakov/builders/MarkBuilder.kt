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
        if (value.isNotEmpty()) {
            markDate = LocalDate.parse(value, dateTimeFormatter)
        } else {
            markDate = LocalDate.parse("01.01.2019", dateTimeFormatter)
        }
    }

    fun build(): Mark {
        if (value == 0) {
            throw IllegalArgumentException("Value cannot be 0")
        }
        if (markDate.isEqual(LocalDate.parse("01.01.2019", dateTimeFormatter))) {
            throw IllegalArgumentException("Provide date, plz")
        }
        return Mark(value, markDate)
    }
}