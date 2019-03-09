package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.WindowManager

/**
 * @author Lin
 * @date 2019/2/19
 * @function 屏幕相关工具类
 * @Description
 */

/**
 * 获取屏幕宽度
 */
fun Context.getScreenWidth(): Int {
    val wm = getSystemService(WINDOW_SERVICE) as WindowManager
    val point = Point()
    when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        true -> wm.defaultDisplay.getRealSize(point)
        false -> wm.defaultDisplay.getSize(point)
    }
    return point.x
}

/**
 * 获取屏幕高度
 */
fun Context.getScreenHeight(): Int {
    val wm = getSystemService(WINDOW_SERVICE) as WindowManager
    val point = Point()
    when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        true -> wm.defaultDisplay.getRealSize(point)
        false -> wm.defaultDisplay.getSize(point)
    }
    return point.y
}

/**
 * 获取屏幕密度
 */
fun getScreenDensity() = Resources.getSystem().displayMetrics.density

/**
 * 获取屏幕DPI
 */
fun getScreenDPI() = Resources.getSystem().displayMetrics.densityDpi

/**
 * 设置横屏
 */
fun Activity.setScreenLandscape() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

/**
 * 设置竖屏
 */
fun Activity.setScreenPortrait() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

/**获取屏幕方向**/
val Context.screenOrientation
    get() = resources.configuration.orientation

/**是否是横屏**/
val Context.isLandscape: Boolean
    get() = screenOrientation == Configuration.ORIENTATION_LANDSCAPE


/**是否是竖屏**/
val Context.isPortrait: Boolean
    get() = screenOrientation == Configuration.ORIENTATION_PORTRAIT





