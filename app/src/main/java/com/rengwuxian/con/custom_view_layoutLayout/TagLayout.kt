package com.rengwuxian.con.custom_view_layoutLayout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    // 存储每一个child的值
    private val childrenBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineWidthUsed = 0
        var lineMaxHeight = 0
        val specWithSize = MeasureSpec.getSize(widthMeasureSpec)
        val specWithMode = MeasureSpec.getMode(widthMeasureSpec)
        // 计算子View的尺寸
        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)

            if (specWithMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > specWithSize) {
                lineWidthUsed = 0
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            }

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBounds = childrenBounds[index]
            childBounds.set(
                lineWidthUsed,
                heightUsed,
                lineWidthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )

            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed, lineWidthUsed)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
        }
        // 计算自己也就是父View的尺寸
        val selfWidth = widthUsed
        val selfHeight = heightUsed + lineMaxHeight
        setMeasuredDimension(selfWidth, selfHeight)
    }

    // 给子View布局
    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        for ((index, child) in children.withIndex()) {
            val childBounds = childrenBounds[index]
            // 遍历每一个子
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}