package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student


class TaskReport(student: Student, task : String) {
    val taskId = task
    val nick = student.nickName
    private val gitRunner = GitRunner()
    val isDock = gitRunner.generateDocs(student, task)
    val testBuild = gitRunner.runTests(student, task)
    val isBuild = testBuild.first
    val isTested = testBuild.second
    val result = TestResult().parseTests(student, task)

    fun printReportTerminal() {
        println("Report for task $taskId")
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
}