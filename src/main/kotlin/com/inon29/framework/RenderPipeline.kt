package com.inon29.framework

import com.inon29.common.Offset
import com.inon29.framework.render.RenderView

class RenderPipeline {
    var renderView: RenderView? = null
    fun flushLayout() {
        renderView!!.performLayout()
    }

    fun flushPaint() {
        val rootLayer = renderView!!.layer
        val context = PaintingContext(rootLayer, renderView!!.size.and(Offset.zero))
        renderView!!.paint(context, Offset.zero)
        context.stopRecordingIfNeeded()
    }
}