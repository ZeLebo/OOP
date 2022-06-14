package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student

class StudentReport(val student: Student) {
    val FILENAME: String = "report.html"
    private val gitRunner = GitRunner()
    private val attendance = gitRunner.checkAttendance(student)
    private val tasksReport = ArrayList<TaskReport>()
    private val marks = student.marks

    private fun generateTasksReport() {
        for (task in student.givenTasks) {
            tasksReport.add(TaskReport(student, task))
        }
    }

    fun printReportTerminal() {
        generateTasksReport()
        println("Student: ${student.nickName}")
        println("Attendance: $attendance")
        println()
        for (taskReport in tasksReport) {
            taskReport.printReportTerminal()
        }
        for (mark in marks) {
            println("Mark: ${mark.value} of ${mark.date}")
        }
        println()
    }

    fun saveReport() {
        generateTasksReport()
        var html = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>${student.nickName} Report</title>
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
        
        h2 {
            font-size: 50px;
        }

        p {
            font-size: 36px;
        }
    </style>
            </head>
            <body>
            <div class="center">
    """
        html += "<h1>${student.nickName} Report</h1>"
        html += "<p>Attendance: $attendance</p>"
        html += "<p>Marks:</p>"
        for (mark in marks) {
            html += "<p>${mark.value} of ${mark.date}</p>"
        }
        html += "<p>Tasks:</p>"
        for (taskReport in tasksReport) {
            html += "<h2>${taskReport.task}</h2>"
            html += "<p>${taskReport.htmlMid()}</p>"
            html += "<p></p>"
        }
        html += "</div>\n</body>\n</html>"
        val file = java.io.File(FILENAME)
        file.writeText(html)
    }
}