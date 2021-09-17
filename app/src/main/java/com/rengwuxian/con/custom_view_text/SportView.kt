package com.rengwuxian.con.custom_view_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.rengwuxian.con.R
import com.rengwuxian.con.custom_view_drawing.dp


private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDTH = 20.dp
private val RADIUS = 150.dp

class SportView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100.dp
        // 绘制的字体样式
        typeface = ResourcesCompat.getFont(context, R.font.font)
        // 文字位置
        textAlign = Paint.Align.CENTER
    }
    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制环
        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        // 绘制进度条
        paint.color = HIGHLIGHT_COLOR
        // 线条的头部样式
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90f,
            225f,
            false,
            paint
        )

        // 绘制文字
        paint.textSize = 100.dp
        paint.style = Paint.Style.FILL
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(
            "aaaa",
            width / 2f,
            height / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f,
            paint
        )

        // 绘制文字2
        paint.textSize = 150.dp
        paint.textAlign = Paint.Align.LEFT
        paint.getFontMetrics(fontMetrics)
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText("abab", -bounds.left.toFloat(), -bounds.top.toFloat(), paint)

        // 绘制文字3
        paint.textSize = 15.dp
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText("abab", -bounds.left.toFloat(), -bounds.top.toFloat(), paint)
    }
}