package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL
import java.io.File
import java.util.concurrent.TimeUnit

class GitRunner {
    // clone repo to the repos folder and run tests
    fun cloneAndRunTests(nick: String, repo: String, dir: String) {
        // check if the repo is already cloned
        val repoDir = File("./repos/$nick")
        if (repoDir.exists()) {
            println("Repo $repo already cloned")
        } else {
            Runtime.getRuntime().exec("git clone $repo repos/$nick").waitFor(2, TimeUnit.SECONDS)
            // delete .git from it to prevent git from messing with it
            Runtime.getRuntime().exec("rm -rf repos/$nick/.git").waitFor(2, TimeUnit.SECONDS)
        }
        // todo count the tests and run them
        // count and run gradle tests
        val result = Runtime.getRuntime().exec("cd repos/$nick/$dir && ./gradlew test --info")
        result.waitFor(2, TimeUnit.SECONDS)

        if (result.exitValue() != 0) {
            println("Test failed for $nick/$repo/$dir")
        } else {
            println("Test passed for $nick/$repo/$dir")
        }

        // count passed tests
        var passed = 0
        var failed = 0
        var skipped = 0
        var total = 0

        // read output
        result.inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                if (it.contains("Tests run: ")) {
                    total = it.split(" ")[2].toInt()
                }
                if (it.contains("Tests passed: ")) {
                    passed = it.split(" ")[2].toInt()
                }
                if (it.contains("Tests failed: ")) {
                    failed = it.split(" ")[2].toInt()
                }
                if (it.contains("Tests skipped: ")) {
                    skipped = it.split(" ")[2].toInt()
                }
            }
        }

        // print results
        println("$nick/$repo/$dir:")
        println("Total: $total")
        println("Passed: $passed")
        println("Failed: $failed")
        println("Skipped: $skipped")
    }
}

fun main() {
    val runner = GitRunner()
    val dsl = DSL()
    runner.cloneAndRunTests(dsl.student.nickName, dsl.student.url, dsl.student.givenTasks[0])
}