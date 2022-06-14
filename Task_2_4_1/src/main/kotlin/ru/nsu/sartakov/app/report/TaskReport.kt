package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student


class TaskReport(student: Student, private val task : String) {
    private val gitRunner = GitRunner()
    private val isDock = gitRunner.generateDocs(student, task)
    private val testBuild = gitRunner.runTests(student, task)
    private val isBuild = testBuild.first
    private val isTested = testBuild.second
    private val result = TestResult().parseTests(student, task)

    fun printReportTerminal() {
        println("Report for task $task")
        when {
            isBuild -> {
                println("Build: ✅")
                if (isTested) {
                    println("Tests: ✅")
                    println("\tTotal: ${this.result.total}")
                    println("\tPassed: ${this.result.passed}")
                    println("\tFailed: ${this.result.failed}")
                    println("\tTime on tests: ${this.result.time}")
                } else {
                    println("Tests: ❌")
                }
            }
            else -> {
                println("Build: ❌")
            }
        }
        if (isDock) {
            println("Docs: ✅")
        }
        else {
            println("Docs: ❌")
        }
        println()
    }
    
    fun saveReport() {
        TODO("save to html file") 
    }
}