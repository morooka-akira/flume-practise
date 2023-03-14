package com.inon29.engine

import com.inon29.common.layertree.LayerTree
import com.inon29.framework.RenderPipeline

class Shell(
    val taskRunners: TaskRunners,
    var glView: GLView,
    var rasterizer: Rasterizer?,
    private val renderPipeline: RenderPipeline,
    private val width: Int,
    private val height: Int,
) {
    fun initRasterThread() {
        taskRunners.rasterTaskRunner.postTask {
            println("in rasterThread")
            val context = glView.createContext()
            rasterizer = Rasterizer(width, height, context)
        }
    }

    fun drawFrame() {
        renderPipeline.flushLayout()
        renderPipeline.flushPaint()
        render()
    }

    private fun render() {
        val layerTree = LayerTree().apply {
            rootLayer = renderPipeline.renderView!!.layer
        }
        taskRunners.rasterTaskRunner.postTask {
            rasterizer!!.drawToSurface(layerTree)
            glView.swapBuffers()
        }
    }
}