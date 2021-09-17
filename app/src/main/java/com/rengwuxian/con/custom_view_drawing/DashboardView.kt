package com.rengwuxian.con.custom_view_drawing

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.math.cos
import kotlin.math.sin

// 椭圆的开合角度
private const val OPEN_ANGLE = 120f
private const val MARK = 10
private val RADIUS = 150f.px
private val LENGTH = 120f.px
private val DASH_WIDTH = 2f.px
private val DASH_LENGTH = 10f.px

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val dash = Path()
    private lateinit var pathEffect: PathEffect

    init {
        paint.strokeWidth = 3f.px
        paint.style = Paint.Style.STROKE
        // 初始化刻度的矩形
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + OPEN_ANGLE / 2f,
            360 - OPEN_ANGLE
        )
        // 测量path的长度
        val pathMeasure = PathMeasure(path, false)
        // 绘制虚线的路径
        pathEffect = PathDashPathEffect(dash, (pathMeasure.length - DASH_WIDTH) / 20f, 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        // 画弧
        canvas.drawPath(path, paint)

        // 画刻度
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        // 指针
        canvas.drawLine(width / 2f, height / 2f, width/ 2f + LENGTH * cos(markToRadians(MARK)).toFloat(),
        height /2f + LENGTH * sin(markToRadians(MARK)).toFloat(), paint)
    }

    private fun markToRadians(mark:Int) = Math.toRadians((90 + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE) / 20f * mark).toDouble())

}