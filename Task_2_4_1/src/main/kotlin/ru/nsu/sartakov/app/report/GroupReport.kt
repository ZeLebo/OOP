package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Group

class GroupReport(group: Group) {
    val group = group
    val students = group.students
    val FILENAME : String = "report.html"

    fun printFullReportTerminal() {
        println("Group: ${group.number}")
        println("Students:")
        for (student in students) {
            println("${student.firstName} ${student.lastName} is ${student.nickName}")
        }
        println()

        for (student in students) {
            StudentReport(student).printReportTerminal()
        }
    }

    fun saveFullReport() {
        val tmp = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Title</title>
            </head>
            <body>

            </body>
            </html>""".trimIndent()
        TODO("save to html file")
    }

    fun printTaskReportTerminal(task: String) {
        println("Group: ${group.number}")
        for (student in students) {
            if (task in student.givenTasks) {
                println(student.nickName)
                println("Attendance: ${student.attendance()}")
                if (student.marks.isNotEmpty()) {
                    println("Marks:")
                    for (mark in student.marks) {
                        println("\t${mark.value} on ${mark.date}")
                    }
                }
                println("")
                TaskReport(student, task).printReportTerminal()
            }
        }
    }

    fun saveTaskReport(task : String) {
        var html = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>${group.number} Report</title>
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
    }
    """
        html += "</div>\n</body>\n</html>"
        val file = java.io.File(FILENAME)
        file.writeText(html)
    }
}