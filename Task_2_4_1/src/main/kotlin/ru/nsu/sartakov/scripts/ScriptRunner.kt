package ru.nsu.sartakov.scripts

import java.io.BufferedReader
import java.io.File
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ScriptRunner {
    inline fun <reified T> run(script: String, extension: String = "kts"): T {
        val scriptEngine : ScriptEngine = ScriptEngineManager().getEngineByExtension(extension)
        val result : Any = scriptEngine.eval(script)
        return if (result is T) {
            result
        } else {
            throw IllegalArgumentException("Script result is not of type ${T::class.qualifiedName}")
        }
    }

    inline fun <reified T> run(file: File, extension: String = "kts") : T {
        val bufferedReader : BufferedReader = file.bufferedReader()
        val fileContent: String = bufferedReader.readText()
        bufferedReader.close()
        return run(fileContent, extension)
    }
}