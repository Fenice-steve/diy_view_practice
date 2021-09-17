package com.rengwuxian.con.custom_view_materialEditText

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.rengwuxian.con.R
import com.rengwuxian.con.custom_view_drawing.dp


/**
 * 参考文章：https://mp.weixin.qq.com/s/oW4kZm-Uzu69n-U8Ywl9SA
 */

private val TEXT_SIZE = 12.dp
private val TEXT_MARGIN = 8.dp
private val HORIZONTAL_OFFSET = 5.dp
private val VERTICAL_OFFSET = 23.dp
private val EXTRA_VERTICAL_OFFSET = 16.dp

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var floatingLabelShown = false

    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                }
            }
        }

    init {
        paint.textSize = TEXT_SIZE

        for (index in 0 until attrs.attributeCount) {
            println("Attrs: key: ${attrs.getAttributeName(index)}, value: ${attrs.getAttributeValue(index)}")
        }

        // 1.此方法去查找attrs文件中自定义的属性，返回一个TypedArray的属性数组
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        // 2.typedArray.getBoolean获取指定的属性的值，指定默认值
        useFloatingLabel =
            typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        // 3.使用完之后进行回收
        typedArray.recycle()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (floatingLabelShown && text.isNullOrEmpty()){
            floatingLabelShown = false
            animator.reverse()
        }else if (!floatingLabelShown && !text.isNullOrEmpty()){
            floatingLabelShown = true
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        val currentVerticalValue = VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction)
        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)
    }

}