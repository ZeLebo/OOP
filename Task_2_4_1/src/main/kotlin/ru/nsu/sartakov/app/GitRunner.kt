package ru.nsu.sartakov.app

class GitRunner {
    // fun to clone repo to the repos directory
    fun clone(repo: String, dir: String) {
        val command = "git clone $repo $dir"
        Runtime.getRuntime().exec(command)
    }

    // clone repo to the repos folder and run tests
    fun cloneAndRunTests(repo: String, dir: String) {
        clone(repo, dir)
        val command = "cd $dir && ./gradlew test"
        Runtime.getRuntime().exec(command)
    }
}