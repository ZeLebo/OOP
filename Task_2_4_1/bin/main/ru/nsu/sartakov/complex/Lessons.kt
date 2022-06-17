package ru.nsu.sartakov.complex

import ru.nsu.sartakov.builders.LessonBuilder
import ru.nsu.sartakov.entities.Lesson

class Lessons: ArrayList<Lesson>() {
    fun lesson(block: LessonBuilder.() -> Unit) =
        add(LessonBuilder().apply(block).build())
}