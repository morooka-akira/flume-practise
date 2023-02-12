package com.inon29.common

abstract class OffsetBase(
    private val dx: Double,
    private val dy: Double,
) {
    val isInfinite: Boolean
        get() = dx >= Double.POSITIVE_INFINITY || dy >= Double.POSITIVE_INFINITY

    val isFinite: Boolean
        get() = dx.isFinite() && dy.isFinite()
}