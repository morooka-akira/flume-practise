package com.inon29.framework.render

import com.inon29.common.Offset

interface ParentData

data class BoxParentData(
    var offset: Offset = Offset.zero
) : ParentData