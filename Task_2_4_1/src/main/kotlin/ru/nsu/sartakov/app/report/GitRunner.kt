package ru.nsu.sartakov.app.report

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Student
import java.io.File
import java.time.LocalDate
import java.util.*
import java.util.concurrent.TimeUnit

typealias BuildTest = Pair<Boolean, Boolean>

/**
 * Class for git work
 * Copy, pull, get commits and work with them
 */
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

    private fun pullClone(student : Student) {
        val repoDir = File("./repos/${student.nickName}")
        if (!repoDir.exists()) {
            // if student url doesn't end with .git, add it
            val url = if (student.url.endsWith(".git")) student.url else "${student.url}.git"
            val result = Runtime.getRuntime().exec("git clone $url repos/${student.nickName}")
            if (result.exitValue() != 0) {
                println("The provided link is wrong, make sure that it is correct")
            }
            result.waitFor(2, TimeUnit.SECONDS)
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

    fun checkAttendance(student : Student) : Float {
        val lessons = DSL().lessons()
        val git = Git(FileRepository("repos/${student.nickName}/.git"))
        var result = 0
        for (lesson in lessons) {
            val start : Date = localDateToDate(lesson.date)
            val end = localDateToDate(lesson.date.plusDays(8))
            for (commit in git.log().call()) {
                val commitDate = commit.authorIdent.`when`
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
                forProjectDirectory(File("./repos/${student.nickName}/${taskId}")).
                useGradleVersion("7.3").connect()
    }

    fun generateDocs(student: Student, taskName: String) : Boolean {
        if (!isCloned(student, taskName)) {
            pullClone(student)
        }
        projectConnect(student, taskName).use { connection ->
            val build = connection.newBuild().forTasks("javadoc")

            val result = try {
                build.run()
                true
            } catch (e: Exception) {
                false
            }
            return result
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
                println("Build for $taskId failed, but the problem in you…")
                return Pair(false, false)
            }

            build = connection.newBuild().forTasks("test")
            val testRes = try {
                build.run()
                true
            } catch (e: Exception) {
                println("Test for $taskId failed, but the problem in you…")
                false
            }

            return BuildTest(buildRes, testRes)
        }
    }
}