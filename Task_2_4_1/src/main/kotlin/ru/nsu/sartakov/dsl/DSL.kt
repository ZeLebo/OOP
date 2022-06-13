package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.builders.StudentBuilder
import ru.nsu.sartakov.complex.Groups
import ru.nsu.sartakov.complex.Lessons
import ru.nsu.sartakov.complex.Tasks
import ru.nsu.sartakov.entities.Student
import java.io.File
import javax.script.ScriptEngineManager


// TODO: conf data from files, not hard code
class DSL {
    private fun student(block: StudentBuilder.() -> Unit): Student {
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

    fun lessons(block: Lessons.() -> Unit): Lessons {
        return Lessons().apply(block)
    }

    // reading a group
    fun groups(block: Groups.() -> Unit): Groups {
        return Groups().apply(block)
    }

    private fun configureGroups(): Groups {
        val textConfig = File("./src/main/kotlin/ru/nsu/sartakov/configs/GroupConf.kts").readText()
        val scriptResult: Groups
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Groups
        }
        return scriptResult
    }

    private fun configureLessons(): Lessons {
        val textConfig = File("./src/main/kotlin/ru/nsu/sartakov/configs/LessonsConf.kts").readText()
        var scriptResult: Lessons
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Lessons
        }
        return scriptResult
    }

    private fun configureTasks(): Tasks {
        val textConfig = File("./src/main/kotlin/ru/nsu/sartakov/configs/TasksConf.kts").readText()
        var scriptResult: Tasks
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Tasks
        }
        return scriptResult
    }

    val tasks by lazy { configureTasks() }
    val lessons by lazy { configureLessons() }
    val groups by lazy { configureGroups() }
}

fun main() {
    val dsl = DSL()
    println(dsl.lessons)
}