package com.inon29.common.layertree

import org.jetbrains.skia.Canvas
import org.jetbrains.skia.DirectContext

data class PaintContext(
    val canvas: Canvas,
    val context: DirectContext
)