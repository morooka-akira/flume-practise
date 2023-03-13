package com.inon29.framework.render

import com.inon29.common.Offset
import com.inon29.framework.PaintingContext
import com.inon29.framework.geometrics.BoxConstraints

class RenderConstrainedBox(
    private val additionalConstraints: BoxConstraints,
    child: RenderBox,
) : RenderProxyBox(child) {

    override fun layout(constraints: BoxConstraints) {
        size = if (child != null) {
            child.layout(additionalConstraints.enforce(constraints))
            child.size
        } else {
            additionalConstraints.enforce(constraints).constrain(size)
        }
    }

    override fun paint(context: PaintingContext, offset: Offset) {
        child?.paint(context, offset)
    }
}