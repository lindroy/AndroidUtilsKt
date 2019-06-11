@file:JvmName("BarUtil")

package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.os.Build
import android.support.annotation.RequiresApi
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2019/6/11
 * @function 虚拟导航栏工具类
 * @Description https://juejin.im/post/5bb5c4e75188255c72285b54
 */

/**
 * 判断手机系统是否有虚拟导航栏
 */
val hasNavigationBar
    get() = navBarResId != 0

val Activity.isNavExist: Boolean
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    get() = when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
        true -> {
            var isExist = false
            window.decorView.setOnApplyWindowInsetsListener { v, insets ->
                if (insets != null) {
                    isExist = insets.systemWindowInsetBottom == navigationBarHeight
                }
                insets
            }
            isExist
        }
        false -> false
    }


/**
 * 获取虚拟导航栏的高度
 * 单位为px
 */
val navigationBarHeight: Int
    get() {
        val resourceId = navBarResId
        return if (resourceId != 0) {
            getResDeminPx(resourceId)
        } else 0
    }

private val navBarResId
    get() = AndUtil.appContext.resources.getIdentifier(
        "navigation_bar_height",
        "dimen", "android"
    )