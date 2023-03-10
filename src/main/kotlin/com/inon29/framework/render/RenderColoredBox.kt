package com.inon29.framework.render

import com.inon29.common.Offset
import com.inon29.framework.PaintingContext
import org.jetbrains.skia.Paint

class RenderColoredBox(
    private val color: Int
) : RenderProxyBox() {
    override fun paint(context: PaintingContext, offset: Offset) {
        if (size.width != 0.0 && size.height != 0.0) {
            context.canvas.drawRect(size.and(offset), Paint().also { it.color = color })
        }
        child?.paint(context, offset)
    }
}