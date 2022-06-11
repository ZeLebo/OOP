package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL
import java.io.File
import java.util.concurrent.TimeUnit

class GitRunner {
    // clone repo to the repos folder and run tests
    fun cloneAndRunTests(nick: String, repo: String, dir: String) {
        // check if the repo is already cloned
        val repoDir = File(dir, repo)
        if (repoDir.exists()) {
            println("Repo $repo already cloned")
        } else {
            Runtime.getRuntime().exec("git clone $repo repos/$nick").waitFor(2, TimeUnit.SECONDS)
        }
        println(Runtime.getRuntime().exec("cd repos/$nick/$dir && ./gradlew test"))
    }
}

fun main() {
    val runner = GitRunner()
    val dsl = DSL()
    runner.cloneAndRunTests(dsl.student.nickName, dsl.student.url, dsl.student.givenTasks[0])
}