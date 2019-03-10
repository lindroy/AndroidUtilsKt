package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.provider.Settings
import android.support.annotation.IntRange
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2019/3/9
 * @function 屏幕亮度工具类
 * @Description
 */

/**
 * 是否是自动亮度
 */
val isAutoBrightness
    get() = try {
        Settings.System.getInt(
            AndUtil.appContext.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE
        ) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
    } catch (e: Settings.SettingNotFoundException) {
        e.printStackTrace()
        false
    }


/**
 * 设置是否开启自动亮度
 * @param enable : 为true时开启，false时关闭
 * @return 设置成功返回true
 */
fun setAutoBrightness(enable: Boolean) = Settings.System.putInt(
    AndUtil.appContext.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
    if (enable) Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC else Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
)


/**
 * 获取系统屏幕亮度
 */
var systemBrightness
    get() = try {
        Settings.System.getInt(AndUtil.appContext.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
    } catch (e: Settings.SettingNotFoundException) {
        e.printStackTrace()
        -1
    }
    set(@IntRange(from = 0, to = 255) brightness) {
        if (isAutoBrightness) {
            //如果当前是自动亮度，则关闭自动亮度
            setAutoBrightness(false)
        }
        val uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS)
        Settings.System.putInt(AndUtil.appContext.contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
        AndUtil.appContext.contentResolver.notifyChange(uri, null)
    }

/**
 * 获取当前窗口亮度
 */
var Activity.windowBrightness
    get() = window.attributes.screenBrightness
    set( brightness) {
        window.attributes = window.attributes.apply {
            screenBrightness = if (brightness < 0) -1.0F else brightness
        }

    }




