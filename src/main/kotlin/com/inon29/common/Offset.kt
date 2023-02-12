package com.inon29.common

class Offset(
    val dx: Double,
    val dy: Double,
) : OffsetBase(dx, dy) {

    operator fun plus(other: Offset): Offset {
        return Offset(dx + other.dx, dy + other.dy)
    }

    operator fun minus(other: Offset): Offset {
        return Offset(dx - other.dx, dy - other.dy)
    }

    operator fun unaryMinus(): Offset {
        return Offset(-dx, dy)
    }

    override fun toString(): String {
        return "Offset(dx: $dx, dy: $dy)"
    }

    companion object {
        val zero = Offset(0.0, 0.0)
    }
}