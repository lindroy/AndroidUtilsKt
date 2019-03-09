package com.lindroid.androidutilskt.extension

import android.view.View

/**
 * @author Lin
 * @date 2019/3/8
 * @function View工具类
 * @Description
 */

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

