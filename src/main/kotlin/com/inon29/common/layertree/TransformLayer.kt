package com.inon29.common.layertree

import com.inon29.common.Offset
import com.inon29.common.mapRect
import org.jetbrains.skia.Matrix33

class TransformLayer(
    var transform: Matrix33 = Matrix33.IDENTITY
) : ContainerLayer() {

    override fun preroll() {
        val childPaintBounds = prerollChildren()
        paintBounds = transform.mapRect(childPaintBounds)
    }

    override fun paint(context: PaintContext) {
        context.canvas.save()
        context.canvas.concat(transform)

        super.paint(context)

        context.canvas.restore()
    }

    companion object {
        fun withOffset(
            transform: Matrix33 = Matrix33.IDENTITY,
            offset: Offset = Offset.zero,
        ): TransformLayer {
            val move = Matrix33.makeTranslate(
                offset.dx.toFloat(),
                offset.dy.toFloat()
            )
            return TransformLayer(transform.makeConcat(move))
        }
    }
}