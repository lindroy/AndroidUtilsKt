@file:JvmName("DensityUtil")
package com.lindroid.androidutilskt.extension

import android.content.Context

/**
 * @author Lin
 * @date 2018/10/18
 * @function 单位转换工具类
 * @Description
 */

fun Context.dp2px(dpValue: Float): Float {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5F)
}

fun Context.dp2px(dpValue: Int) = dp2px(dpValue.toFloat()).toInt()

fun Context.px2dp(pxValue: Float): Float {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5F)
}

fun Context.px2dp(pxValue: Int) = px2dp(pxValue.toFloat()).toInt()

fun Context.sp2px(spValue: Float): Float {
    val fontScale = resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5F)
}

fun Context.sp2px(spValue: Int) = sp2px(spValue.toFloat()).toInt()

fun Context.px2sp(pxValue: Float): Float {
    val fontScale = resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5F)
}

fun Context.px2sp(pxValue: Int) = px2sp(pxValue.toFloat()).toInt()
