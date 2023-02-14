package com.inon29.framework.render

import com.inon29.common.Size

abstract class RenderBox(
    override var size: Size = Size.zero
) : RenderObject()