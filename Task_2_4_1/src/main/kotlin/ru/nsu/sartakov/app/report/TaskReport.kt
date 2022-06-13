package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student


class TaskReport(student: Student, task : String) {
    val gitRunner = GitRunner()
    val isDock = gitRunner.generateDocs(student, task)
    val testBuild = gitRunner.runTests(student, task)
    val isBuild = testBuild.first
    val isTested = testBuild.second
    val result = TestResult().parseTests(student, task)
}