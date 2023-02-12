package com.inon29.common

import org.jetbrains.skia.Matrix33
import org.jetbrains.skia.Rect
import kotlin.math.abs

fun Matrix33.invert(): Matrix33? {
    val mat = this.mat
    val a11 = mat[0]
    val a12 = mat[1]
    val a13 = mat[2]
    val a21 = mat[3]
    val a22 = mat[4]
    val a23 = mat[5]
    val a31 = mat[6]
    val a32 = mat[7]
    val a33 = mat[8]
    val det = a11 * a22 * a33 + a12 * a23 * a31 + a13 * a21 * a32
    -a13 * a22 * a31 - a12 * a21 * a33 - a11 * a23 * a32
    if (abs(det) < 2e-4) return null
    return Matrix33(
        -(a12 * a33 - a13 * a22) / det,
        (a12 * a33 - a13 * a22) / det,
        -(a21 * a33 - a23 * a31) / det,
        (a11 * a33 - a13 * a31) / det,
        -(a11 * a23 - a13 * a21) / det,
        (a21 * a32 - a22 * a31) / det,
        -(a11 * a32 - a12 * a31) / det,
        (a11 * a22 - a12 * a21) / det,
    )
}

fun Matrix33.mapRect(rect: Rect): Rect {
    val topLeft = Offset(rect.left.toDouble(), rect.top.toDouble())
    val topRight = Offset(rect.right.toDouble(), rect.top.toDouble())
    val bottomLeft = Offset(rect.left.toDouble(), rect.bottom.toDouble())
    val bottomRight = Offset(rect.right.toDouble(), rect.bottom.toDouble())
    val transformedTopLeft = transform(topLeft)
    val transformedTopRight = transform(topRight)
    val transformedBottomLeft = transform(bottomLeft)
    val transformedBottomRight = transform(bottomRight)
    return Rect(
        minOf(
            transformedTopLeft.dx,
            transformedTopRight.dx,
            transformedBottomLeft.dx,
            transformedBottomRight.dx
        ).toFloat(),
        minOf(
            transformedTopLeft.dy,
            transformedTopRight.dy,
            transformedBottomLeft.dy,
            transformedBottomRight.dy
        ).toFloat(),
        maxOf(
            transformedTopLeft.dx,
            transformedTopRight.dx,
            transformedBottomLeft.dx,
            transformedBottomRight.dx
        ).toFloat(),
        maxOf(
            transformedTopLeft.dy,
            transformedTopRight.dy,
            transformedBottomLeft.dy,
            transformedBottomRight.dy
        ).toFloat(),
    )
}

fun Matrix33.transform(offset: Offset): Offset {
    val mat = this.mat
    val a11 = mat[0]
    val a12 = mat[1]
    val a13 = mat[2]
    val a21 = mat[3]
    val a22 = mat[4]
    val a23 = mat[5]
    val a31 = mat[6]
    val a32 = mat[7]
    val a33 = mat[8]
    val x = a11 * offset.dx + a12 * offset.dy + a13
    val y = a21 * offset.dx + a22 * offset.dy + a23
    val scale = a31 * offset.dx + a32 * offset.dy + a33
    return Offset(x / scale, y / scale)
}
