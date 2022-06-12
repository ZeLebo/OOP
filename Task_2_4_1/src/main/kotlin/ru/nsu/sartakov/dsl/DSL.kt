package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.builders.GroupBuilder
import ru.nsu.sartakov.builders.StudentBuilder
import ru.nsu.sartakov.complex.Lessons
import ru.nsu.sartakov.complex.Tasks
import ru.nsu.sartakov.entities.Group
import ru.nsu.sartakov.entities.Student
import java.io.File
import javax.script.ScriptEngineManager


// TODO: conf data from files, not hard code
class DSL {
    fun student(block: StudentBuilder.() -> Unit): Student {
        return StudentBuilder().apply(block).build()
    }

    val student = student {
        nickName = "ZeLebo"
        firstName = "Alexander"
        lastName = "Sartakov"
        url = "https://github.com/ZeLebo/OOP.git"

        givenTasks("Task_1_1_1", "Task_1_1_2")

        marks {
            mark {
                value = 5
                date = "01.01.2022"
            }
            mark {
                value = 4
                date = "01.01.2022"
            }
        }
    }

    fun tasks(block: Tasks.() -> Unit): Tasks {
        return Tasks().apply(block)
    }

//    val tasks = tasks{
//        task {
//            taskId = "Task_1_1_1"
//            deadLine = "01.01.2022"
//            score = 5
//        }
//        task {
//            taskId = "Task_1_1_2"
//            score = 5
//            deadLine = "01.01.2022"
//        }
//    }

    fun lessons(block: Lessons.() -> Unit): Lessons {
        return Lessons().apply(block)
    }

//    val lessons = lessons{
//        lesson {
//            date = "01.01.2022"
//            attendance = true
//        }
//        lesson {
//            date = "01.01.2023"
//            attendance = false
//        }
//    }

    // reading a group
    fun group(block: GroupBuilder.() -> Unit): Group {
        return GroupBuilder().apply(block).build()
    }

    fun configureGroup() : Group {
        val textConfig = File("./src/main/kotlin/ru/nsu/sartakov/configs/GroupConf.kts").readText()
        with (ScriptEngineManager().getEngineByExtension("kts")) {
            return eval(textConfig) as Group
        }
    }

    fun configureLesson() : Lessons {
        val textConfig = File("./src/main/kotlin/ru/nsu/sartakov/configs/LessonsConf.kts").readText()
        var scriptResult : Lessons
        with (ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Lessons
        }
        return scriptResult
    }

    fun configureTasks() : Tasks {
        val textConfig = File("./src/main/kotlin/ru/nsu/sartakov/configs/TasksConf.kts").readText()
        var scriptResult : Tasks
        with (ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Tasks
        }
        return scriptResult
    }

    //val tasks = configureTasks()


    val group = configureGroup()
//    val lessons = configureLesson()

    fun test() {
        val group = configureGroup()
        println(group.toString())
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No command line args")
    } else {
        if (args[0] == "test") {
            DSL().test()
        } else if (args[0] == "build") {
            println("Building")
            return
        } else {
            println("Unknown command line arg")
            return
        }
    }
}