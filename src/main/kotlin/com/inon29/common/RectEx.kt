package com.inon29.common

import org.jetbrains.skia.Rect
import kotlin.math.max
import kotlin.math.min

fun Rect.join(other: Rect): Rect {
    return Rect(
        min(this.left, other.left),
        min(this.top, other.top),
        max(this.right, other.right),
        min(this.bottom, other.bottom),
    )
}

fun Rect.makeOffset(dx: Float, dy: Float): Rect {
    return Rect(
        this.left + dx,
        this.top + dy,
        this.right + dx,
        this.bottom + dy,
    )
}

fun Rect.makeOffset(offset: Offset): Rect {
    return makeOffset(
        offset.dx.toFloat(),
        offset.dy.toFloat(),
    )
}

val kEmptyRect: Rect = Rect.makeWH(0f, 0f)

val kGiantRect: Rect = Rect.makeLTRB(-1e9f, -1e9f, 1e9f, 1e9f)
