@file:JvmName("BarUtil")

package com.lindroid.androidutilskt.extension

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import android.view.WindowManager
import com.lindroid.androidutilskt.app.AndUtil
import com.lindroid.androidutilskt.extension.logcat.dt

/**
 * @author Lin
 * @date 2019/6/11
 * @function 虚拟导航栏工具类
 * @Description
 */

/**
 * 判断手机系统是否有虚拟导航栏
 */
val hasNavigationBar
    get() = when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        true -> {
            val wm = AndUtil.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            val realSize = Point()
            display.getSize(size)
            display.getRealSize(realSize)
            " realSize.x=${realSize.x}".dt("Tag")
            " size.x=${size.x}".dt("Tag")
            " realSize.y=${realSize.y}".dt("Tag")
            " size.y=${size.y}".dt("Tag")
            realSize.x != size.x || realSize.y != size.y
        }
        false -> {
            //是否有物理菜单键
            val hasMenuKey = ViewConfiguration.get(AndUtil.appContext).hasPermanentMenuKey()
            //是否有物理返回键
            val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            !hasMenuKey && !hasBackKey
        }
    }