package com.inon29.engine

class TaskRunners(
    val rasterTaskRunner: TaskRunner,
    val uiTaskRunner: TaskRunner
) {
    fun terminateAll() {
        rasterTaskRunner.terminate()
        uiTaskRunner.terminate()
    }
}