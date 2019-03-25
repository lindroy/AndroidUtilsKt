@file:JvmName("ViewUtil")

package com.lindroid.androidutilskt.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

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
fun View.toBitmap(scale: Float = 1.0F): Bitmap? = when (this) {
    is ImageView -> {
        val drawable: Drawable = drawable
        (drawable as BitmapDrawable).bitmap
    }
    else -> {
        clearFocus()
        val bitmap =
            createBitmapSafely((width * scale).toInt(), (height * scale).toInt())
        if (bitmap != null) {
            val canvas = Canvas()
            synchronized(canvas) {
                with(canvas) {
                    setBitmap(bitmap)
                    save()
                    drawColor(Color.WHITE)
                    scale(scale, scale)
                    draw(canvas)
                    restore()
                    setBitmap(null)
                }
            }
        }
        bitmap


    }
}

/**
 * 安全创建Bitmap，如果产生了OOM，可以主动GC后再尝试。
 * https://github.com/QMUI/QMUI_Android
 * @param width : Bitmap宽度
 * @param height : Bitmap高度度
 * @param retryCount : 重试次数，默认一次
 * @param config : Bitmap.Config，默认为ARGB_8888
 * @return 创建Bitmap成功返回Bitmap，否则返回null。
 */
private fun createBitmapSafely(
    width: Int,
    height: Int,
    retryCount: Int = 1,
    config: Bitmap.Config = Bitmap.Config.ARGB_8888
): Bitmap? = try {
    Bitmap.createBitmap(width, height, config)
} catch (e: OutOfMemoryError) {
    e.printStackTrace()
    when (retryCount > 0) {
        true -> {
            System.gc()
            createBitmapSafely(width, height, retryCount, config)
        }
        false -> null
    }
}

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

