package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Task

class App {
    // run tests from task
    fun run(task: Task) {
        println(task.toString())
    }
}

fun main(args: Array<String>) {
    // check for command line args
    if (args.isEmpty()) {
        println("No command line args")
    } else {
        // if arg is "test" run test
        if (args[0] == "test") {
            DSL().test()
            val dsl = DSL()
            dsl.tasks.forEach { println(it.toString()) }
            // print all student info
            println(dsl.student.toString())
        } else if (args[0] == "build") {
            println("Building")
            return
        } else {
            println("Unknown command line arg")
            // print arg
            println(args[0])
            return
        }
    }
}