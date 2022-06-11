package ru.nsu.sartakov.app

import ru.nsu.sartakov.dsl.DSL
import java.io.File

class GitRunner {
    // fun to clone repo to the repos directory
    // repo - url to the repo
    // dir - name of task to test
    fun clone(repo: String) {
        val command = "cd ./repos; git clone $repo"
        Runtime.getRuntime().exec(command)
    }

    // clone repo to the repos folder and run tests
    fun cloneAndRunTests(repo: String, dir: String) {
        clone(repo)
        val command = "cd repos/$dir && ./gradlew test; cd .. && rm -rf $dir"
        Runtime.getRuntime().exec(command)
    }
}

fun main() {
    val runner = GitRunner()
    val dsl = DSL()
    runner.cloneAndRunTests(dsl.student.url, dsl.student.givenTasks[0])
}