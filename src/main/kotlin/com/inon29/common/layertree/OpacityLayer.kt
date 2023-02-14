package com.inon29.common.layertree

import org.jetbrains.skia.Paint

class OpacityLayer(
    var alpha: Int? = null
) : ContainerLayer() {
    override fun paint(context: PaintContext) {
        val paint = Paint()
        if (alpha != null) {
            paint.alpha = alpha!!
        }
        context.canvas.saveLayer(paintBounds, paint)
        super.paint(context)
        context.canvas.restore()
    }
}