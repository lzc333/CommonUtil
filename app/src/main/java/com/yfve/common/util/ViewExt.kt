package com.yfve.common.util
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup

/**
 * 针对 Linearlayout 垂直布局中view隐藏和显示的动画，原理:动态改变其LayoutParams.height的值
 */
fun <T : View> T.heightVisibleAnimator(isVisible:Boolean){
    var tag = getTag(1123460103)
    if(tag!=null&& tag == isVisible){
        return
    }
    setTag(1123460103,isVisible)
    var viewHeight: Int = height
    if (viewHeight == 0) {
        val width = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        val height = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        measure(width, height)
        viewHeight = measuredHeight
    }

    val animator = if(isVisible) ValueAnimator.ofInt(0, viewHeight) else ValueAnimator.ofInt( viewHeight ,0)
    animator.addUpdateListener { animation ->
        val params: ViewGroup.LayoutParams = this.layoutParams
        params.height = animation.animatedValue as Int
        layoutParams = params
    }
    animator.start()
}


/**
 * dp值转换为px
 */
fun <T : View> T.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * px值转换成dp
 */
fun <T : View> T.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/***
 * 点击事件的View扩展
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener {
    if (clickEnable()) {
        block(it as T)
    }
}

fun <T:View> T.longClick(block: (T) -> Unit) = setOnLongClickListener{
    block(it as T)
    true
}

/***
 * 带延迟过滤的点击事件View扩展
 * @param delay Long 延迟时间，默认800毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.clickWithTrigger(time: Long = 800, block: (T) -> Unit) {
    triggerDelay = time
    setOnClickListener {
        if (clickEnable()) {
            block(it as T)
        }
    }
}

private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460113) != null) getTag(1123460113) as Long else 0
    set(value) {
        setTag(1123460113, value)
    }

private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else -1
    set(value) {
        setTag(1123461123, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}
