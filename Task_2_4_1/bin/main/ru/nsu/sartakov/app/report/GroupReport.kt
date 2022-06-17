package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Group

class GroupReport(val group: Group) {
    val students = group.students
    val FILENAME : String = "report.html"
    var studentsReport = ArrayList<StudentReport>()

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

    private fun generateStudentsReport() {
        for (student in students) {
            studentsReport.add(StudentReport(student))
        }
    }

    fun saveFullReport() {
        var html = ""
        html += DSL().fileFinder("preparedStyle.html").readText()
        html += "<body>\n" +
                "<div class=\"center\">"
        html = html.replace("Title", "${group.number} Report")
        html += "<h1>${group.number} Report</h1>"
        generateStudentsReport()
        html += "<h2>Students:</h2>"
        students.forEach {
            html += "<p>${it.firstName} ${it.lastName} is ${it.nickName}</p>"
        }
        for (studentReport in studentsReport) {
            html += "<pre>\n\n\n</pre>"
            var tmp = studentReport.studentReportLogic()
            tmp = tmp.replace("<h2>", "<h3>")
            tmp = tmp.replace("</h2>", "</h3>")
            tmp = tmp.replace("<h1>", "<h2>")
            tmp = tmp.replace("</h1>", "</h2>")
            html += tmp
        }
        html += "</div>\n</body>\n</html>"
        val file = java.io.File(FILENAME)
        file.writeText(html)
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
        var html = ""
        html += DSL().fileFinder("preparedStyle.html").readText()
        html += "<body>\n" +
                "<div class=\"center\">"
        html = html.replace("Title", "${group.number} Report for $task")
        html += "<h1>${group.number} Report for $task</h1>"
        for (student in students) {
            if (task in student.givenTasks) {
                html += "<pre>\n\n\n</pre>"
                html += "<p>${student.nickName}</p>"
                html += "<p>Attendance: ${student.attendance()}</p>"
                if (student.marks.isNotEmpty()) {
                    html += "<p>Marks:</p>"
                    for (mark in student.marks) {
                        html += "<p>\t${mark.value} on ${mark.date}</p>"
                    }
                }
                html += "<pre>\n\n\n</pre>"
                html += "<p>${TaskReport(student, task).taskResultLogic()}</p>"
                html += "<pre>\n\n\n</pre>"
            }
        }
        html += "</div>\n</body>\n</html>"
        val file = java.io.File(FILENAME)
        file.writeText(html)
    }
}