package com.inon29.framework.geometrics

import com.inon29.common.Size

class BoxConstraints(
    val minWidth: Double = 0.0,
    val maxWidth: Double = Double.POSITIVE_INFINITY,
    val minHeight: Double = 0.0,
    val maxHeight: Double = Double.POSITIVE_INFINITY
) {

    fun enforce(
        constraints: BoxConstraints
    ): BoxConstraints {
        return BoxConstraints(
            minWidth.coerceIn(constraints.minWidth, constraints.maxWidth),
            maxWidth.coerceIn(constraints.minWidth, constraints.maxWidth),
            minHeight.coerceIn(constraints.minHeight, constraints.maxHeight),
            maxHeight.coerceIn(constraints.minHeight, constraints.maxHeight),
        )
    }

    fun constrainWidth(width: Double): Double {
        return width.coerceIn(minWidth..maxWidth)
    }

    fun constrainHeight(height: Double): Double {
        return height.coerceIn(minHeight..maxHeight)
    }

    fun constrain(size: Size): Size {
        return Size(constrainWidth(size.width), constrainHeight(size.height))
    }

    fun loosen() = BoxConstraints(
        maxWidth = maxWidth,
        maxHeight = maxHeight
    )

    companion object {
        fun tight(size: Size): BoxConstraints {
            return BoxConstraints(
                size.width,
                size.width,
                size.height,
                size.height
            )
        }

        fun tightFor(width: Double?, height: Double?): BoxConstraints {
            return BoxConstraints(
                width ?: 0.0,
                width ?: Double.POSITIVE_INFINITY,
                height ?: 0.0,
                height ?: Double.POSITIVE_INFINITY
            )
        }
    }
}