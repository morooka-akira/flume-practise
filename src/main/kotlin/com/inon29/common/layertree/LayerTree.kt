package com.inon29.common.layertree

class LayerTree {
    var rootLayer: Layer? = null

    fun preroll() {
        assert(rootLayer != null)

        rootLayer!!.preroll()
    }

    fun paint(context: PaintContext) {
        rootLayer?.paint(context)
    }
}