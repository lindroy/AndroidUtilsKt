@file:JvmName("BarUtil")

package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.view.View
import android.view.ViewGroup
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
val hasNavBar
    get() = navBarResId != 0

/*val Activity.isNavExist: Boolean
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    get() = when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
        true -> {
            var isExist = false
            window.decorView.setOnApplyWindowInsetsListener { v, insets ->
                if (insets != null) {
                    isExist = insets.systemWindowInsetBottom == navBarHeight
                }
                insets
            }
            isExist
        }
        false -> false
    }*/

/**
 * 当前是否显示虚拟导航栏
 */
val Activity.isNavBarShowed: Boolean
    get() {
        val viewGroup = window.decorView as ViewGroup? ?: return false
        return (0 until viewGroup.childCount).firstOrNull {
            viewGroup.getChildAt(it).id != View.NO_ID
                    && resources.getResourceEntryName(viewGroup.getChildAt(it).id) == "navigationBarBackground"
        } != null

    }

/**
 * 获取虚拟导航栏的高度，必须在布局绘制完成之后才能获取到正确的值（可以在onWindowFocusChanged()中调用）
 * 单位为px
 */
val navBarHeight: Int
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