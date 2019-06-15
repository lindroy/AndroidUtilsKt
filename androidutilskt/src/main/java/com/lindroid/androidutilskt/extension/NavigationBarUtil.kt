@file:JvmName("BarUtil")

package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.lindroid.androidutilskt.app.AndUtil


/**
 * @author Lin
 * @date 2019/6/11
 * @function 虚拟导航栏工具类
 * @Description
 */

/**
 * 判断手机系统是否有虚拟导航栏
 */
val hasNavBar
    @JvmName("hasNavBar")
    get() = navBarResId != 0

/*fun Window.setOnNavBarStatusWatcher(callback: (isShowed: Boolean) -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH && hasNavBar) {
        val navigationBarHeight = navBarHeight
        decorView.setOnApplyWindowInsetsListener { v, insets ->
            if (insets != null) {
                callback.invoke(insets.systemWindowInsetBottom == navigationBarHeight)
            }
            insets
        }
    }
}

fun Activity.setOnNavBarStatusWatcher(callback: (isShowed: Boolean) -> Unit) {
    window.setOnNavBarStatusWatcher(callback)
}*/

/**
 * 当前虚拟导航栏是否显示
 */
val Activity.isNavBarShowed: Boolean
    get() = this.window.isNavBarShowed

/**
 * 当前虚拟导航栏是否显示
 */
val Window.isNavBarShowed: Boolean
    get() {
        val viewGroup = decorView as ViewGroup? ?: return false
        return (0 until viewGroup.childCount).firstOrNull {
            viewGroup.getChildAt(it).id != View.NO_ID
                    && AndUtil.appContext.resources.getResourceEntryName(viewGroup.getChildAt(it).id) == RES_NAME_NAV_BAR
        } != null
    }

/**
 * 当前虚拟导航栏是否隐藏
 */
val Window.isNavBarHidden: Boolean
    get() = !isNavBarShowed

/**
 * 当前虚拟导航栏是否隐藏
 */
val Activity.isNavBarHidden: Boolean
    get() = window.isNavBarHidden

/*fun Window.hideNavBar() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        return
    }
    *//* val viewGroup = decorView as ViewGroup? ?: return false
     (0 until viewGroup.childCount).forEach {
         val childId = viewGroup.getChildAt(it).id
         if (childId != View.NO_ID){
             val resourceEntryName = AndUtil.appContext.resources.getResourceEntryName(childId)
         }else{
             return
         }
     }*//*
    val uiOptions =
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
}*/

fun Window.setShowNavBar(isShow: Boolean) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT || !hasNavBar) {
        return
    }
    val viewGroup = decorView as ViewGroup? ?: return
    for (i in (0 until viewGroup.childCount)) {
        val childId = viewGroup.getChildAt(i).id
        if (childId != View.NO_ID) {
            val resourceEntryName = AndUtil.appContext.resources.getResourceEntryName(childId)
            if (RES_NAME_NAV_BAR == resourceEntryName) {
                viewGroup.getChildAt(i).visibility = if (isShow) View.VISIBLE else View.INVISIBLE
            }
        } else {
            break
        }
    }
    val uiOptions =
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    if (isShow) {
        decorView.systemUiVisibility = decorView.systemUiVisibility and uiOptions.inv()
    } else {
        decorView.systemUiVisibility = decorView.systemUiVisibility or uiOptions
    }
}


/**
 * 获取虚拟导航栏的高度，必须在布局绘制完成之后才能获取到正确的值（可以在onWindowFocusChanged()中调用）
 * 单位为px
 */
val navBarHeight: Int
    get() {
        val resourceId = navBarResId
        return if (resourceId != 0) {
            getResDimenPx(resourceId)
        } else 0
    }

/**
 * 设置导航栏颜色
 */
var Window.navBarColor: Int
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = navigationBarColor
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    set(@ColorInt value) {
        navigationBarColor = value
    }

/**
 * 设置导航栏颜色
 */
var Activity.navBarColor: Int
    get() = window.navBarColor
    set(@ColorInt value) {
        window.navBarColor = value
    }

/**
 * 设置导航栏颜色
 */
fun Window.setNavBarColorRes(@ColorRes colorId: Int) {
    navBarColor = getResColor(colorId)
}

/**
 * 设置导航栏颜色
 */
fun Activity.setNavBarColorRes(@ColorRes colorId: Int) {
    navBarColor = getResColor(colorId)
}

private const val RES_NAME_NAV_BAR = "navigationBarBackground"

private val navBarResId
    get() = AndUtil.appContext.resources.getIdentifier(
        "navigation_bar_height",
        "dimen", "android"
    )