package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.content.Context
import android.provider.Settings
import android.support.annotation.IntRange

/**
 * @author Lin
 * @date 2019/3/9
 * @function 屏幕亮度工具类
 * @Description
 */

/**
 * 获取系统屏幕亮度
 */

var Context.systemBrightness
    get() = try {
        Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS)
    } catch (e: Settings.SettingNotFoundException) {
        e.printStackTrace()
        -1
    }
    set(@IntRange(from = 0, to = 255) value) {
        val uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS)
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, value)
        contentResolver.notifyChange(uri, null)
    }

/**
 * 设置系统亮度
 */
//fun Context.setSystemBrightness(@IntRange(from = 0, to = 255) brightness: Int) {
//    val uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS)
//    Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
//    contentResolver.notifyChange(uri, null)
//}

/**
 * 获取当前窗口亮度
 */
var Activity.windowBrightness
    get() = window.attributes.screenBrightness
    set(brightness) {
        val lp = window.attributes
        lp.screenBrightness = if (brightness < 0) -1.0F else brightness
        window.attributes = lp
    }

/**
 * 设置当前窗口亮度
 * @param brightness 亮度范围0~1.0，1.0为最亮,-1.0时为默认值
 */
//fun Activity.setWindowBrightness(brightness: Float = -1.0F) {
//    val lp = window.attributes
//    lp.screenBrightness = if (brightness < 0) -1.0F else brightness
//    window.attributes = lp
//}
