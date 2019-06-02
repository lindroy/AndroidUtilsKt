package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver

/**
 * @author Lin
 * @date 2019/6/1
 * @function 软键盘显示和隐藏
 * @Description
 */

internal class KeyboardStatusWatcher(val view: View) : ViewTreeObserver.OnGlobalLayoutListener {

    private val mContext = view.rootView.context

    private val rootView = view.rootView

    private val watchers: HashMap<View, ((isShowed: Boolean, keyboardHeight: Int) -> Unit)> = HashMap()

    var isKeyboardShowed = false

    var keyboardHeight = 0

    init {
        if (!watchers.containsKey(view)) {
            rootView.viewTreeObserver.addOnGlobalLayoutListener(this)
        }
    }

    /**
     * Callback method to be invoked when the global layout state or the visibility of views
     * within the view tree changes
     */
    override fun onGlobalLayout() {
        val rect = Rect()
        rootView.getWindowVisibleDisplayFrame(rect)
        val heightDiff = rootView.height - (rect.bottom - rect.top)
        if (heightDiff > screenHeight / 3) {
            isKeyboardShowed = true
            //计算软键盘高度
            keyboardHeight = when (mContext) {
                is Activity -> {
                    //全屏时减去状态栏高度
                    if (mContext.isFullScreen) heightDiff - screenHeight else heightDiff
                }
                else -> heightDiff
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

    fun addKeyboardStatusWatcher(watcher: ((isShowed: Boolean, keyboardHeight: Int) -> Unit)) {
        watchers[view] = watcher
    }

    fun removeKeyboardStatusWatcher(view: View): Boolean = watchers.remove(view) != null


}