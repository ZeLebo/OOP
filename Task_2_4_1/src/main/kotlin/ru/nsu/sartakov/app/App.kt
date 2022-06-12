package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Task


/**
 * Class for running tests and stuff like this
 * All the args are gere
 */

class App {
    fun checkTask(task: String) : Boolean {
        return false
    }

    fun run() {

    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No command line args")
        return
    }
    // app student nickname run task
    // app student nickname get tasks
    // app student nickname run all
    // app group groupnumber run task
    // app group groupnumber get tasks
    // app group groupnumber run all
    if (args[0] == "student") {
        if (args.size < 3) {
            return
        }
        if (args[1] == "get") {
            if (args[2] == "tasks") {
                println("TODO: get tasks")
            } else {
                println("TODO: get task")
            }
        } else if (args[1] == "run") {
            if (args[2] == "all") {
                println("TODO: run all")
            } else {
                println("TODO: run task")
            }
        }
    } else if (args[0] == "group") {
        if (args.size < 3) {
            return
        }
        if (args[1] == "get") {
            if (args[2] == "tasks") {
                println("TODO: get tasks")
            } else {
                println("TODO: get task")
            }
        } else if (args[1] == "run") {
            if (args[2] == "all") {
                println("TODO: run all")
            } else {
                println("TODO: run task")
            }
        }
    }
}