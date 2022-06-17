package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.complex.Groups
import ru.nsu.sartakov.complex.Lessons
import ru.nsu.sartakov.complex.Students
import ru.nsu.sartakov.complex.Tasks
import java.io.File
import javax.script.ScriptEngineManager
import kotlin.system.exitProcess

class DSL {
    fun tasks(block: Tasks.() -> Unit): Tasks {
        return Tasks().apply(block)
    }

    fun lessons(block: Lessons.() -> Unit): Lessons {
        return Lessons().apply(block)
    }

    fun groups(block: Groups.() -> Unit): Groups {
        return Groups().apply(block)
    }

    fun students(block: Students.() -> Unit): Students {
        return Students().apply(block)
    }

    fun groups(): Groups {
        val students = students()
        val groups = Groups()
        // add student to group with his number in group
        for (student in students) {
            // add student to group with his number in group
            val group = student.group
            if (groups.getGroup(group) == null) {
                groups.addGroup(group)
            }
            groups.getGroup(group)?.addStudent(student)
        }
        return groups
    }

    fun fileFinder(fileName: String): File {
        // list the files from current directory with .kts extension
        File("./").walkBottomUp().forEach { file ->
            if (file.name == fileName) {
                return file
            }
        }
        // create file with fileName if not found
        val resultFile = File(fileName)
        resultFile.createNewFile()

        return resultFile
    }

    fun students() : Students {
        val textConfig = fileFinder("StudentsConf.kts").readText()
        var scriptResult: Students
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            try {
                scriptResult = eval(textConfig) as Students
            } catch (e: Exception) {
                println("Error in script: ${e.message}")
                exitProcess(1)
            }
        }
        return scriptResult
    }

    fun lessons(): Lessons {
        val textConfig = fileFinder("LessonsConf.kts").readText()
        var scriptResult: Lessons
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            try {
                scriptResult = eval(textConfig) as Lessons
            } catch (e: Exception) {
                println("Error in script: ${e.message}")
                exitProcess(1)
            }
        }
        return scriptResult
    }

    fun tasks(): Tasks {
        val textConfig = fileFinder("TasksConf.kts").readText()
        var scriptResult: Tasks
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            try {
                scriptResult = eval(textConfig) as Tasks
            } catch (e: Exception) {
                println("Error in script: ${e.message}")
                exitProcess(1)
            }
        }
        return scriptResult
    }
}

//fun dsl(init: DSL.() -> Unit) = DSL().apply(init)