package ru.nsu.sartakov.complex

import ru.nsu.sartakov.builders.MarkBuilder
import ru.nsu.sartakov.entities.Mark

class Marks: ArrayList<Mark>() {
    fun mark(block: MarkBuilder.() -> Unit) {
        add(MarkBuilder().apply(block).build())
    }
}