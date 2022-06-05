package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.builders.StudentBuilder

class DSL {
    fun student(nickName: String, init: StudentBuilder.() -> Unit) {
        StudentBuilder(nickName).init()
    }
}

fun main() {
    val dsl = DSL()
}