package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL
import ru.nsu.sartakov.entities.Student
import java.io.File
import java.util.concurrent.TimeUnit
import org.gradle.tooling.GradleConnector


class GitRunner {
    /**
     * function to clone all the repositories
     */
    private fun clone(student : Student) {
        val repoDir = File("./repos/${student.nickName}")
        if (!repoDir.exists()) {
            Runtime.getRuntime().exec("git clone ${student.url} repos/${student.nickName}").waitFor(2, TimeUnit.SECONDS)
            // delete .git from it to prevent git from messing with it
            Runtime.getRuntime().exec("rm -rf repos/${student.nickName}/.git").waitFor(2, TimeUnit.SECONDS)
        }
    }

    fun generateDocs(student: Student, taskName: String) {
        TODO("I will generate docs for $student for $taskName later")
    }

    fun runTests(student: Student, taskId : String) {
        if (!File("./repos/${student.nickName}/$taskId").exists()) {
            println("You do not have a repository for task $taskId, cloning it...")
            clone(student)
        }
        // todo how to count the tests and beaufitfy the output
        val connection = GradleConnector.newConnector().forProjectDirectory(File("./repos/${student.nickName}/$taskId")).connect()
        // run tests and get the result
        connection.use {
            connection.newBuild().forTasks("test").run().let {
                println(it.toString())
            }
        }
        //connection.close()
        println("Tests for task $taskId are done")
        connection.close()
    }
}

fun main() {
    val runner = GitRunner()
    val dsl = DSL()
    runner.runTests(dsl.student, "Task_1_1_1")
}