package com.rengwuxian.con

import android.animation.*
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rengwuxian.con.custom_view_animation.ProvinceEvaluator
import com.rengwuxian.con.custom_view_drawing.dp
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * CircleView
         */
//        view.animate()
//            .translationX(200.dp)
//            .translationY(100.dp)
//            .alpha(0.5f)
//            .scaleX(2f)
//            .scaleY(2f)
//            .rotation(90f)
//            .startDelay = 1000
//
//        // 使⽤ ObjectAnimator.ofXxx() 来创建对象，以及使⽤
//        // ObjectAnimator.start() 来主动启动动画。它的优势在于，可以为⾃定义属
//        // 性设置动画。
//        val animator = ObjectAnimator.ofFloat(view, "radius", 150.dp)
//        animator.startDelay = 1000
//        animator.start()

        /**
         * CameraView
         * 将多个 Animator 合并在⼀起使⽤，先后顺序或并列顺序都可以：
         */
//        val bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 60f)
//        bottomFlipAnimator.startDelay = 1000
//        bottomFlipAnimator.duration = 1000
//
//        val flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270f)
//        flipRotationAnimator.startDelay = 200
//        flipRotationAnimator.duration = 1000
//
//        val topFlipAnimator = ObjectAnimator.ofFloat(view,"topFlip", -60f)
//        topFlipAnimator.startDelay = 200
//        topFlipAnimator.duration = 1000
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
//        animatorSet.start()

        // PropertyValuesHolder⽤于设置更加详细的动画，例如多个属性应⽤于同⼀个对象：
//        val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 60f)
//        val flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270f)
//        val topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", - 60f)
//        val holderAnimator = ObjectAnimator.ofPropertyValuesHolder(view, bottomFlipHolder, flipRotationHolder, topFlipHolder)
//        holderAnimator.startDelay = 1000
//        holderAnimator.duration = 2000
//        holderAnimator.start()

        // 配合使⽤ Keyframe
//        val length = 200.dp
//        val keyframe1 = Keyframe.ofFloat(0f, 0f)
//        val keyframe2 = Keyframe.ofFloat(0.2f, 1.5f * length)
//        val keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * length)
//        val keyframe4 = Keyframe.ofFloat(1f, 1f * length)
//        val keyframeHolder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4)
//        val animator = ObjectAnimator.ofPropertyValuesHolder(view, keyframeHolder)
//        animator.startDelay = 1000
//        animator.duration = 2000
//        animator.start()

        /**
         * PontFView
         * ⽤于设置动画完成度到属性具体值的计算公式。默认的 ofInt() ofFloat() 已
            经有了⾃带的 IntEvaluator FloatEvaluator ，但有的时候需要⾃⼰设置
            Evaluator。例如，对于颜⾊，需要为 int 类型的颜⾊设置 ArgbEvaluator，⽽不是
            让它们使⽤ IntEvaluator：
         */
//        val animator = ObjectAnimator.ofObject(view, "point", PointFEvaluator(), PointF(100.dp, 200.dp))
//        animator.startDelay = 1000
//        animator.duration = 10000
//        animator.start()

        /**
         * ProvinceView
         */
        val animator = ObjectAnimator.ofObject(view, "province", ProvinceEvaluator(), "澳门特别行政区")
        animator.startDelay = 1000
        animator.duration = 10000
        animator.start()

    }

    class PointFEvaluator : TypeEvaluator<PointF>{
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val startX = startValue.x
            val endX = endValue.x
            val currentX = startX + (endX - startX) * fraction
            val startY = startValue.y
            val endY = endValue.y
            val currentY = startY + (endY - startY) * fraction
            return PointF(currentX, currentY)
        }
    }
}