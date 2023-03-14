package com.inon29.framework.render

import com.inon29.common.Offset
import com.inon29.common.Size
import com.inon29.common.layertree.ContainerLayer
import com.inon29.common.layertree.TransformLayer
import com.inon29.framework.PaintingContext
import com.inon29.framework.geometrics.BoxConstraints

class RenderView(
    width: Double,
    height: Double
) : RenderObject() {
    override var size: Size = Size(width, height)
    var child: RenderBox? = null
    val layer: ContainerLayer = TransformLayer()

    override fun layout(constraints: BoxConstraints) {
        throw NotImplementedError()
    }

    fun performLayout() {
        child?.layout(BoxConstraints.tight(size))
    }

    override fun paint(context: PaintingContext, offset: Offset) {
        if (child != null) {
            child!!.paint(context, offset)
        }
    }
}