package com.inon29.framework

import com.inon29.common.Offset
import com.inon29.common.layertree.ContainerLayer
import com.inon29.common.layertree.PictureLayer
import org.jetbrains.skia.Canvas
import org.jetbrains.skia.PictureRecorder
import org.jetbrains.skia.Rect

class PaintingContext(
    private val containerLayer: ContainerLayer, private val estimatedBounds: Rect
) {
    private var currentLayer: PictureLayer? = null
    private var recorder: PictureRecorder? = null
    private var _canvas: Canvas? = null
    private val isRecording: Boolean
        get() = _canvas != null

    val canvas: Canvas
        get() {
            if (_canvas == null) startRecording()
            return _canvas!!
        }

    private fun startRecording() {
        currentLayer = PictureLayer()
        recorder = PictureRecorder()
        _canvas = recorder!!.beginRecording(estimatedBounds)
        containerLayer.children.add(currentLayer!!)
    }

    fun stopRecordingIfNeeded() {
        if (!isRecording) return

        currentLayer!!.picture = recorder!!.finishRecordingAsPicture()
        currentLayer = null
        recorder = null
        _canvas = null
    }

    fun pushLayer(
        childLayer: ContainerLayer, painter: PaintingContextCallback, offset: Offset, childPaintBounds: Rect? = null
    ) {
        if (childLayer.children.isNotEmpty()) {
            childLayer.children.clear()
        }

        stopRecordingIfNeeded()
        containerLayer.children.add(childLayer)

        val childContext = PaintingContext(
            childLayer, childPaintBounds ?: estimatedBounds
        )
        painter(childContext, offset)
        childContext.stopRecordingIfNeeded()
    }
}

typealias PaintingContextCallback = (PaintingContext, Offset) -> Unit