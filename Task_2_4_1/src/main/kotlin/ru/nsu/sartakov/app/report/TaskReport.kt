package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Student


class TaskReport(val student: Student, val task : String) {
    val FILENAME : String = "./report.html"
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

    fun taskResultLogic() : String {
        var html = ""
        html += "<p><strong>Build </strong> ${if (isBuild) "✅ PASSED" else "❌ FAILED"}</p>"
        html += "<p><strong>Tests </strong> ${if (isTested) "✅ PASSED" else "❌ FAILED"}</p>"
        if (isTested) {
            html += "<p>Total: ${result.total}</p>"
            html += "<p>Passed: ${result.passed}</p>"
            html += "<p>Failed: ${result.failed}</p>"
            html += "<p><strong>Time on tests:</strong> ${result.time}</p>"
        }
        html += "<p><strong>Docs </strong> ${if (isDock) "✅ PASSED" else "❌ FAILED"}</p>"
        return html
    }
    
    fun saveReport() {
        var html = ""
        html += DSL().fileFinder("preparedStyle.html").readText()
        html += "<body>\n" +
                "<div class=\"center\">"
        html = html.replace("Title", task)
        html += "<h2>${student.nickName}'s report for ${task}</h2>"
        html += "<h3>${student.firstName} ${student.lastName}</h3>"
        html += taskResultLogic()
        html += "</div>\n</body>\n</html>"
        val file = java.io.File(FILENAME)
        file.writeText(html)
    }
}