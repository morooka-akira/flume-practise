package com.inon29.common.layertree

import com.inon29.common.join
import com.inon29.common.kEmptyRect
import org.jetbrains.skia.Rect

open class ContainerLayer : Layer() {
    val children: MutableList<Layer> = mutableListOf()
    override fun paint(context: PaintContext) {
        for (child in children) {
            child.paint(context)
        }
    }

    override fun preroll() {
        paintBounds = prerollChildren()
    }

    protected fun prerollChildren(): Rect {
        var bounds = kEmptyRect
        for (child in children) {
            child.preroll()
            bounds = bounds.join(child.paintBounds)
        }
        return bounds
    }
}
