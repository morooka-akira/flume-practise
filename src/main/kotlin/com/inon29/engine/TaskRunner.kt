package com.inon29.engine

class TaskRunner {
    private val loop = MessageLoop()
    private var terminated = false

    init {
        println("create task")
        loop.start()
    }

    fun postTask(task: () -> Unit) {
        if (!terminated) {
            loop.postTask(task)
        }
    }

    fun terminate() {
        terminated = true
        loop.terminate()
    }
}