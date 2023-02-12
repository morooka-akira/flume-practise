package com.inon29.common

import org.jetbrains.skia.Rect

class Size(
    val width: Double,
    val height: Double,
) : OffsetBase(width, height) {
    val isEmpty = width <= 0.0 || height <= 0.0

    operator fun minus(other: OffsetBase): OffsetBase {
        if (other is Size) {
            return Offset(width - other.width, height - other.height)
        }
        return when (other) {
            is Size ->
                Offset(width - other.width, height - other.height)

            is Offset ->
                Offset(width - other.dx, height - other.dy)

            else -> throw IllegalArgumentException()
        }

    }

    fun and(other: Offset): Rect {
        return Rect.makeXYWH(
            other.dx.toFloat(),
            other.dy.toFloat(),
            width.toFloat(),
            height.toFloat(),
        )
    }

    fun contains(offset: Offset): Boolean {
        return offset.dx >= 0.0 && offset.dx < width && offset.dy >= 0.0 && offset.dy < height
    }

    companion object {
        val zero = Size(0.0, 0.0)
    }
}