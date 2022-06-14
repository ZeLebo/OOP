package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student


class TaskReport(student: Student, val task : String) {
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
        var html = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>${task}</title>
                <style>
        *,
        html {
            margin: 0;
            padding: 0;
            border: 0;
        }

        html {
            width: 100%;
            height: 100%;
        }

        body {
            width: 100%;
            height: 100%;
            position: relative;
            background-color: rgb(243, 229, 171);
        }

        .center {
            width: 100%;
            height: 50%;
            margin: 0;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: black;
            font-family: "Trebuchet MS", Helvetica, sans-serif;
            text-align: center;
        }

        h1 {
            font-size: 80px;
        }

        p {
            font-size: 36px;
        }
    </style>
            </head>
            <body>
            <div class="center">
            <h1>${task}</h1>
             """.trimIndent()
        html += taskResultLogic()
        html += "</div>\n</body>\n</html>"
        val file = java.io.File(FILENAME)
        file.writeText(html)
    }
}