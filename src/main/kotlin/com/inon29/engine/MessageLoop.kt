package com.inon29.engine

class MessageLoop : Thread() {
    private var running = true
    private val taskQueue = ArrayDeque<() -> Unit>()

    override fun run() {
        super.run()

        while (running) {
            // スリープを多少入れないと機能しない
            sleep(10)
            runExpiredTasks()
        }
    }

    private fun runExpiredTasks() {
        while (!taskQueue.isEmpty()) {
            val invocation = taskQueue.removeFirst()
            invocation()
        }
    }

    fun postTask(task: () -> Unit) {
        if (!running) return
        println("postTask")
        taskQueue.addLast(task)
    }

    fun terminate() {
        running = false
    }
}