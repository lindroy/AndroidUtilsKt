@file:JvmName("ViewUtil")

package com.lindroid.androidutilskt.extension

import android.graphics.Bitmap
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2019/3/8
 * @function View工具类
 * @Description
 */

/**
 * 根据布局id填充一个布局
 */
@JvmOverloads
fun inflate(@LayoutRes layoutId: Int, root: ViewGroup? = null) = View.inflate(AndUtil.appContext, layoutId, root)

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
fun View.setInvisible() {
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
fun View.setNewPadding(
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


/**
 * 获取TextView的String内容
 */
val TextView.textString: String
    get() = text.toString()

/**
 * 获取TextView的String内容长度
 */
val TextView.textLength: Int
    get() = text.length

/**
 * 判断TextView的内容是否为空
 */
val TextView.isTextEmpty: Boolean
    get() = text.isEmpty()

/**
 * 判断TextView的内容是否为null或空
 */
val TextView.isTextNullOrEmpty: Boolean
    get() = text.isNullOrEmpty()

/**
 * 判断TextView的内容是否为非空
 */
val TextView.isTextNotEmpty: Boolean
    get() = !isTextEmpty

/**
 * 判断TextView的内容是否为空白
 */
val TextView.isTextBlank: Boolean
    get() = text.isBlank()

/**
 * 判断TextView的内容是否为null或空白
 */
val TextView.isTextNullOrBlank: Boolean
    get() = text.isNullOrBlank()

/**
 * 判断TextView的内容是否为非空白
 */
val TextView.isTextNotBlank: Boolean
    get() = text.isNotBlank()