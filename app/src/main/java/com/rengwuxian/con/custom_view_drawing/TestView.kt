package com.rengwuxian.con.custom_view_drawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

private val RADIUS = 100f.px

class TestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CCW)
        path.addRect(width / 2f - RADIUS, height/ 2f, width / 2f + RADIUS, height/ 2f + 2 * RADIUS, Path.Direction.CCW)
        path.addCircle(width / 2f, height / 2f, RADIUS * 1.5f, Path.Direction.CCW)
        // 使用哪种绘制效果取决于美工
        path.fillType = Path.FillType.INVERSE_EVEN_ODD
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

}