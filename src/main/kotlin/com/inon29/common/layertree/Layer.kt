package com.inon29.common.layertree

import org.jetbrains.skia.Rect

abstract class Layer {
    var paintBounds: Rect = Rect.makeWH(0f, 0f)

    abstract fun paint(context: PaintContext)
    abstract fun preroll()
}