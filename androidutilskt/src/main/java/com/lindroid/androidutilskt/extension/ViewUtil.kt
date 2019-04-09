@file:JvmName("ViewUtil")

package com.lindroid.androidutilskt.extension

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import com.lindroid.androidutilskt.extension.logcat.d

/**
 * @author Lin
 * @date 2019/3/8
 * @function View工具类
 * @Description
 */

/**
 * 当前View是否可见
 */
val View.isVisible
    get() = visibility == View.VISIBLE

/**
 * 当前View是否不可见
 */
val View.isInvisible
    get() = visibility == View.INVISIBLE

/**
 * 当前View是否隐藏
 */
val View.isGone
    get() = visibility == View.GONE

/**
 * 将View设置为隐藏
 */
fun View.setGone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

/**
 * 将View设置为可见
 */
fun View.setVisible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

/**
 * 将View设置为不可见
 */
fun View.setInVisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

/**
 * 设置View的宽度
 * @param width: 宽度值，单位为px
 */
fun View.setWidth(width: Int) {
    layoutParams = layoutParams.apply {
        this.width = width
    }
}

/**
 * 设置View的高度
 * @param height: 高度值，单位为px
 */
fun View.setHeight(height: Int) {
    layoutParams = layoutParams.apply {
        this.height = height
    }
}

/**
 * 设置View的宽度和高度
 * @param width: 宽度值，单位为px
 * @param height: 高度值，单位为px
 */
fun View.setWidthAndHeight(width: Int, height: Int) {
    layoutParams = layoutParams.apply {
        this.width = width
        this.height = height
    }
}

/**
 * 将View转换为Bitmap
 * @param  scale: 生成的Bitmap相对于原View的大小比例，范围为0~1.0
 */
fun View.toBitmap(scale: Float = 1.0F): Bitmap? = viewToBitmap(this, scale)


/**
 * 设置View的padding
 */
fun View.setPadding(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

/**
 * 测量View
 */
private fun View.measureView() {
    var params = layoutParams
    if (params == null) {
        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    val widthSpec = ViewGroup.getChildMeasureSpec(0, 0, params.width)
    params.width.d()
    val heightSpec = if (params.height > 0) {
        View.MeasureSpec.makeMeasureSpec(params.height, View.MeasureSpec.EXACTLY)
    } else {
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    }
    measure(widthSpec, heightSpec)
}

/**
 * 获取View的高度
 * 如果是“math_parent”属性则无法获取，值为0。
 */
val View.viewHeight: Int
    get() {
        measureView()
        return measuredHeight
    }

/**
 * 获取View的宽度
 * 如果是“math_parent”属性则无法获取，值为0。
 */
val View.viewWidth: Int
    get() {
        measureView()
        return measuredWidth
    }
