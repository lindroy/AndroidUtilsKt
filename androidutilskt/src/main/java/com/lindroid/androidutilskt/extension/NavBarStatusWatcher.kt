package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import com.lindroid.androidutilskt.extension.statusbar.statusBarHeight

/**
 * @author Lin
 * @date 2019/6/14
 * @function 监听虚拟导航栏的显示和隐藏
 * @Description
 */
class NavBarStatusWatcher(val view: View) : ViewTreeObserver.OnGlobalLayoutListener {
    private val mContext = view.context

    private val rootView = view.rootView

    private val watchers: HashMap<View, ((isShowed: Boolean) -> Unit)> = HashMap()

    init {
        if (hasNavBar && !watchers.containsKey(view)) {
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
        val barHeight = when (mContext) {
            is Activity -> {
                if (mContext.isFullScreen) heightDiff else heightDiff - statusBarHeight
            }
            else -> heightDiff
        }
        watchers.forEach {
            it.value.invoke(barHeight == navBarHeight)
        }
    }

    fun addNavBarStatusWatcher(watcher: (isShowed: Boolean) -> Unit) {
        watchers[view] = watcher
    }

}