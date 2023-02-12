package com.inon29.common.layertree

import org.jetbrains.skia.Picture

class PictureLayer() : Layer() {
    var picture: Picture? = null

    override fun paint(context: PaintContext) {
        picture?.playback(context.canvas)
    }

    override fun preroll() {
        paintBounds = picture!!.cullRect
    }
}