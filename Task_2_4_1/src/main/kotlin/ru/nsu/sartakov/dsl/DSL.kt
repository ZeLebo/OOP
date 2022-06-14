package ru.nsu.sartakov.dsl

import ru.nsu.sartakov.complex.Groups
import ru.nsu.sartakov.complex.Lessons
import ru.nsu.sartakov.complex.Students
import ru.nsu.sartakov.complex.Tasks
import java.io.File
import javax.script.ScriptEngineManager

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
        val groups : Groups = Groups()
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
        var resultFile : File = File("")
        val files = File("./").walkBottomUp().forEach { file ->
            if (file.name == fileName) {
                resultFile = file
                return resultFile
            }
        }
        return resultFile
    }

    fun students() : Students {
        val textConfig = fileFinder("StudentsConf.kts").readText()
        val scriptResult: Students
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Students
        }
        return scriptResult
    }

    fun lessons(): Lessons {
        val textConfig = fileFinder("LessonsConf.kts").readText()
        var scriptResult: Lessons
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Lessons
        }
        return scriptResult
    }

    fun tasks(): Tasks {
        val textConfig = fileFinder("TasksConf.kts").readText()
        var scriptResult: Tasks
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            scriptResult = eval(textConfig) as Tasks
        }
        return scriptResult
    }
}

