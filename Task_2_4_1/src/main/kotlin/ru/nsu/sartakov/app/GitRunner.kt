package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL
import java.io.File
import java.util.concurrent.TimeUnit


class GitRunner {
    /**
     * function to clone all the repositories
     */
    private fun clone(nick: String, repo: String) {
        val repoDir = File("./repos/$nick")
        if (!repoDir.exists()) {
            Runtime.getRuntime().exec("git clone $repo repos/$nick").waitFor(2, TimeUnit.SECONDS)
            // delete .git from it to prevent git from messing with it
            Runtime.getRuntime().exec("rm -rf repos/$nick/.git").waitFor(2, TimeUnit.SECONDS)
        }
    }

    // run tests for specific repo
    fun runTests(nick: String, repo: String, dir: String) {
        // check if the repo is already cloned
        clone(nick, repo)
        // run tests and get the output
        val result = Runtime.getRuntime().exec("cd repos/$nick/$dir && gradle test")
        result.waitFor()
        println(result.inputStream.readBytes().decodeToString())
        // TODO: 12.06.2022 - read the output to collect tests results


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
                if (it.contains("PASSED")) {
                    passed += 1
                    total += 1
                }
                if (it.contains("FAILED")) {
                    failed += 1
                    total += 1
                }
                if (it.contains("SKIPPED")) {
                    skipped += 1
                    total += 1
                }
            }
        }

        // print results
        println("Total: $total")
        println("Passed: $passed")
        println("Failed: $failed")
        println("Skipped: $skipped")
    }
}

fun main() {
    val runner = GitRunner()
    val dsl = DSL()
    runner.runTests(dsl.student.nickName, dsl.student.url, dsl.student.givenTasks[0])
}