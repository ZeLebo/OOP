package ru.nsu.sartakov.builders

import ru.nsu.sartakov.entities.Lesson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LessonBuilder {
    private val dateFormatter : DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private var lessonDate: LocalDate = LocalDate.parse("01.01.2019", dateFormatter)

    var date: String = ""
        set(value) {
            if (value.isEmpty()) {
                lessonDate = LocalDate.parse(value, dateFormatter)
            }
            lessonDate = LocalDate.parse("01.01.1999", dateFormatter)
        }

    var attendance: Boolean = false
    fun build(): Lesson {
        if (lessonDate.isBefore(LocalDate.parse("01.01.1990", dateFormatter))) {
            throw IllegalArgumentException("Date is empty")
        }
        return Lesson(lessonDate, attendance)
    }
}