package ru.nsu.sartakov.app

import org.gradle.tooling.GradleConnector
import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Student
import java.io.File
import java.util.concurrent.TimeUnit

typealias BuildTest = Pair<Boolean, Boolean>

class GitRunner {
    /**
     * function to clone all the repositories
     */
    private fun clone(student : Student) {
        val repoDir = File("./repos/${student.nickName}")
        if (!repoDir.exists()) {
            Runtime.getRuntime().exec("git clone ${student.url} repos/${student.nickName}").
                waitFor(2, TimeUnit.SECONDS)
            // delete .git from it to prevent git from messing with it
            Runtime.getRuntime().exec("rm -rf repos/${student.nickName}/.git").
                waitFor(2, TimeUnit.SECONDS)
        }
    }

    fun generateDocs(student: Student, taskName: String) {
        TODO("I will generate docs for $student for $taskName later")
    }

    fun runTests(student: Student, taskId : String) : BuildTest {
        if (!File("./repos/${student.nickName}/$taskId").exists()) {
            println("You do not have a repository for task $taskId, cloning it...")
            clone(student)
        }

        // if build or tests are failed, we will return false
        GradleConnector.newConnector().
        forProjectDirectory(File("./repos/${student.nickName}/$taskId")).
        connect().use { connection ->
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
//     println(test.first)
}