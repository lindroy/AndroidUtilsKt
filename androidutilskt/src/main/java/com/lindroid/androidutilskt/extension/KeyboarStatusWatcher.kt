package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import com.lindroid.androidutilskt.extension.logcat.d
import com.lindroid.androidutilskt.extension.statusbar.statusBarHeight

/**
 * @author Lin
 * @date 2019/6/1
 * @function 监听软键盘显示和隐藏，并获取其高度
 * @Description
 */

class KeyboardStatusWatcher(val view: View) : ViewTreeObserver.OnGlobalLayoutListener {

    private val mContext = view.context

    private val rootView = view.rootView

    private val watchers: HashMap<View, ((isShowed: Boolean, keyboardHeight: Int) -> Unit)> = HashMap()

    private var visibleHeight = 0

    /**
     * 软键盘是否显示
     */
    var isKeyboardShowed = false
        private set(value) {
            field = value
        }

    /**
     * 软键盘是否隐藏
     */
    val isKeyboardHidden
        get() = !isKeyboardShowed

    /**
     * 最近一次弹出的软键盘高度
     */
    var keyboardHeight = 0

    init {
        if (!watchers.containsKey(view)) {
            "注册监听".d()
            view.viewTreeObserver.addOnGlobalLayoutListener(this)
        }
    }

    /**
     * Callback method to be invoked when the global layout state or the visibility of views
     * within the view tree changes
     */
    override fun onGlobalLayout() {
        "监听软键盘".d()
        val rect = Rect()
        rootView.getWindowVisibleDisplayFrame(rect)
        if (visibleHeight == (rect.bottom - rect.top)) {
            return
        } else {
            visibleHeight = (rect.bottom - rect.top)
        }
        val heightDiff = rootView.height - (rect.bottom - rect.top)
        if (heightDiff > screenHeight / 3) {
            isKeyboardShowed = true
            //计算软键盘高度
            when (mContext) {
                is Activity -> {
                    //非全屏时减去状态栏高度
                    keyboardHeight = if (mContext.isFullScreen) heightDiff else heightDiff - statusBarHeight
                    //导航栏显示时减去其高度
                    if (hasNavBar && mContext.isNavBarShowed) {
                        keyboardHeight -= navBarHeight
                    }
                    Log.e("Tag","hasNavBar=$hasNavBar")
                    Log.e("Tag","mContext.isNavBarShowed=${mContext.isNavBarShowed}")
                    Log.e("Tag","statusBarHeight=$statusBarHeight")
                    Log.e("Tag","navBarHeight=$navBarHeight")
                    Log.e("Tag","keyboardHeight=$keyboardHeight")
                }
                else -> keyboardHeight = heightDiff
            }
        } else {
            //软键盘隐藏时键盘高度为0
            isKeyboardShowed = false
            keyboardHeight = 0
        }

        watchers.forEach {
            it.value.invoke(isKeyboardShowed, keyboardHeight)
        }
    }

    /**
     * 监听软键盘状态
     */
    fun addKeyboardStatusWatcher(watcher: ((isShowed: Boolean, keyboardHeight: Int) -> Unit)) {
        watchers[view] = watcher
    }

    /**
     * 移除软键盘状态监听事件
     */
    fun removeKeyboardStatusWatcher(view: View): Boolean = watchers.remove(view) != null

}