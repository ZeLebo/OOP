package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.dsl.DSL
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

    fun studentReportLogic() : String {
        if (tasksReport.isEmpty()) {
            generateTasksReport()
        }
        var html = ""
        html += "<h1>${student.nickName} Report</h1>"
        html += "<h2>${student.firstName} ${student.lastName}</h2>"
        html += "<p>Attendance: $attendance</p>"
        html += "<p>Marks:</p>"
        for (mark in marks) {
            html += "<p>${mark.value} of ${mark.date}</p>"
        }
        html += "<p>Tasks:</p>"
        for (taskReport in tasksReport) {
            html += "<h2>${taskReport.task}</h2>"
            html += "<p>${taskReport.taskResultLogic()}</p>"
        }
        return html
    }

    fun saveReport() {
        var html = ""
        html += DSL().fileFinder("preparedStyle.html").readText()
        html += "<body>\n" +
                "<div class=\"center\">"
        html = html.replace("Title", "${student.nickName} Report")
        html += studentReportLogic()
        html += "</div>\n</body>\n</html>"
        val file = java.io.File(FILENAME)
        file.writeText(html)
    }
}