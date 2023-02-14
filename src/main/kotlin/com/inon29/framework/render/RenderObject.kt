package com.inon29.framework.render

import com.inon29.common.Offset
import com.inon29.common.Size
import com.inon29.framework.PaintingContext
import com.inon29.framework.geometrics.BoxConstraints

abstract class RenderObject {
    var parentData: ParentData? = null

    abstract var size: Size

    abstract fun layout(constraints: BoxConstraints)

    abstract fun paint(context: PaintingContext, offset: Offset)
}