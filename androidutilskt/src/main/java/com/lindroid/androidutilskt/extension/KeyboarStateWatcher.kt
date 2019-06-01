package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver

/**
 * @author Lin
 * @date 2019/6/1
 * @function
 * @Description
 */

class KeyboarStateWatcher(val view: View) : ViewTreeObserver.OnGlobalLayoutListener {

    private val mContext = view.rootView.context

    private val callback: ((hasShow: Boolean, keyboardHeight: Int) -> Unit)? = null
    /**
     * Callback method to be invoked when the global layout state or the visibility of views
     * within the view tree changes
     */
    override fun onGlobalLayout() {
        val rect = Rect()
        val rootView = view.rootView
        rootView.getWindowVisibleDisplayFrame(rect)
        val heightDiff = rootView.height - (rect.bottom - rect.top)
        if (heightDiff > screenHeight / 3) {
            when (mContext) {
                is Activity -> {
                    if (mContext.isFullScreen) {
                        callback?.invoke(true, heightDiff - screenHeight)
                    } else {
                        callback?.invoke(true, heightDiff)
                    }
                }
                else -> callback?.invoke(true, heightDiff)
            }
        } else {
            callback?.invoke(false, 0)
        }

    }


}