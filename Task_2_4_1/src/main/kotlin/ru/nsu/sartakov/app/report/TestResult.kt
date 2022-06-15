package ru.nsu.sartakov.app.report

import ru.nsu.sartakov.entities.Student
import java.io.File

class TestResult {
    var total = 0
    var skipped = 0
    var failed = 0
    var passed = 0
    var time = 0.0

    // parse tests from xml file
    fun parseTests(student: Student, taskId: String) : TestResult {
        // get the fir xml file from
        // check if ./repos/${student.nickName}/$taskId/build/test-results/test directory exists
        if (!File("./repos/${student.nickName}/$taskId/build/test-results/test").exists()) {
            return TestResult()
        }
        // get first file from ./repos/${student.nickName}/$taskId/build/test-results/test/
        val testDir = File("./repos/${student.nickName}/$taskId/build/test-results/test/")
        val testFiles = testDir.listFiles()
        if (testFiles.isEmpty()) {
            return TestResult()
        }
        val testFile = testFiles[0]
        if (!testFile.name.endsWith(".xml")) {
            return TestResult()
        }
        val testXml = testFile.readText()
        // if found tests="{number}" extract it
        var regex = "tests=\"(.*?)\"".toRegex()
        total = regex.find(testXml)?.groupValues?.get(1)?.toInt()!!
        regex = "skipped=\"(.*?)\"".toRegex()
        skipped = regex.find(testXml)?.groupValues?.get(1)?.toInt()!!
        regex = "failures=\"(.*?)\"".toRegex()
        failed = regex.find(testXml)?.groupValues?.get(1)?.toInt()!!
        passed = total - failed
        regex = "time=\"(.*?)\"".toRegex()
        time = regex.find(testXml)?.groupValues?.get(1)?.toDouble()!!
        return this
    }
}