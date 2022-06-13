package ru.nsu.sartakov.app

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import ru.nsu.sartakov.complex.Lessons
import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Student
import java.io.File
import java.time.LocalDate
import java.util.*
import java.util.concurrent.TimeUnit

typealias BuildTest = Pair<Boolean, Boolean>

class GitRunner {
    private fun isCloned(student: Student, taskId: String) : Boolean {
        if (!File("./repos/${student.nickName}/$taskId").exists()) {
            return false
        }
        return true
    }

    private fun pull(student : Student) {
        Runtime.getRuntime().exec("cd repos/${student.nickName} && git pull").
        waitFor(2, TimeUnit.SECONDS)
    }

    // todo: if cloned, pull if not - clone logic
    private fun pullClone(student : Student) {
        val repoDir = File("./repos/${student.nickName}")
        if (!repoDir.exists()) {
            Runtime.getRuntime().exec("git clone ${student.url} repos/${student.nickName}").
                waitFor(2, TimeUnit.SECONDS)
        }
        else {
            pull(student)
        }
    }

    // function to convert localDate to date
    private fun localDateToDate(localDate: LocalDate): Date {
        val date = Date()
        date.time = localDate.toEpochDay() * 24 * 60 * 60 * 1000
        return date
    }


    fun checkAttendance(student : Student, lessons: Lessons) : Float {
        val git = Git(FileRepository("repos/${student.nickName}/.git"))
        var result = 0
        for (lesson in lessons) {
            val start : Date = localDateToDate(lesson.date)
            val end = localDateToDate(lesson.date.plusDays(8))
            for (commit in git.log().call()) {
                val commitDate = commit.authorIdent.`when`
                println("$commitDate $start $end")
                if (commitDate.after(start) && commitDate.before(end)) {
                    result++
                    break
                }
            }
        }
        return result/lessons.size.toFloat()
    }

    private fun projectConnect(student: Student, taskId: String) : ProjectConnection {
        return GradleConnector.newConnector().
                forProjectDirectory(File("./repos/${student.nickName}/${taskId}")).connect()
    }

    // todo : return later
    fun generateDocs(student: Student, taskName: String) {
        if (!isCloned(student, taskName)) {
            pullClone(student)
        }
        projectConnect(student, taskName).use {
            connection ->
            val tmp = connection.newBuild().forTasks("java:doc").run()
            println(tmp.toString())
        }
    }

    fun runTests(student: Student, taskId : String) : BuildTest {
        if (!isCloned(student, taskId)) {
            println("You need to clone ${student.nickName}/$taskId, cloning...")
            pullClone(student)
        }

        // if build or tests are failed, we will return false
        projectConnect(student, taskId).use { connection ->
            var build = connection.newBuild().forTasks("build")

            val buildRes = try {
                build.run()
                true
            } catch (e: Exception) {
                println("Build failed")
                e.printStackTrace()
                false
            }

            build = connection.newBuild().forTasks("test")
            val testRes = try {
                build.run()
                true
            } catch (e: Exception) {
                println("Test failed")
                e.printStackTrace()
                false
            }

            return BuildTest(buildRes, testRes)
        }
    }
}

fun main() {
    val runner = GitRunner()
    val dsl = DSL()
    val test = runner.runTests(dsl.student, "Task_1_1_1")
    println(test.toString())
    println(runner.checkAttendance(dsl.student, dsl.lessons))
}