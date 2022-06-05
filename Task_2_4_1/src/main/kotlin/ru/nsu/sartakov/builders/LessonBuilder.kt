package ru.nsu.sartakov.builders

import ru.nsu.sartakov.entities.Lesson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LessonBuilder {
    private val dateFormatter : DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private var lessonDate: LocalDate = LocalDate.parse("01.01.2019", dateFormatter)
    var attendance: Boolean = false

    var date: String = ""
        set(value) {
            if (value.isNotEmpty()) {
                lessonDate = LocalDate.parse(value, dateFormatter)
            } else {
                lessonDate = LocalDate.parse("01.01.2019", dateFormatter)
            }
        }

    fun build(): Lesson {
        if (lessonDate.isEqual(LocalDate.parse("01.01.2019", dateFormatter))) {
            throw IllegalArgumentException("Date is empty")
        }
        return Lesson(lessonDate, attendance)
    }
}