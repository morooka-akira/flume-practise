package com.inon29.framework.render

import com.inon29.common.Offset
import com.inon29.framework.PaintingContext
import com.inon29.framework.geometrics.BoxConstraints

abstract class RenderProxyBox(
    val child: RenderBox? = null,
) : RenderBox() {
    override fun layout(constraints: BoxConstraints) {
        size = if (child != null) {
            child.layout(constraints)
            child.size
        } else {
            constraints.smallest
        }
    }

    override fun paint(context: PaintingContext, offset: Offset) {
        child?.paint(context, offset)
    }
}