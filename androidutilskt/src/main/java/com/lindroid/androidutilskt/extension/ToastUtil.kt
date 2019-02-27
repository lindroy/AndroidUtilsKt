package com.lindroid.androidutilskt.extension

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * @author Lin
 * @date 2018/10/29
 * @function Toast工具类
 * @Description
 */
private var toast: Toast? = null
private var firstShowTime = 0L
private var nextShowTime = 0L

private fun Context.showToast(message: CharSequence, duration: Int): Toast {
    fun durationTime() = if (duration == Toast.LENGTH_SHORT) 2000L else 3500L
    return when (toast == null) {
        true -> {
            firstShowTime = System.currentTimeMillis()
            toast = Toast.makeText(this, null, duration)
            toast!!.apply {
                //这里调用setText
                setText(message)
            }
        }
        false -> toast!!.apply {
            nextShowTime = System.currentTimeMillis()
            setText(message)
            setDuration(duration)
        }
    }.apply {
        if (nextShowTime == 0L || nextShowTime - firstShowTime > durationTime()) {
            show()
            Handler(Looper.getMainLooper()).postDelayed({
                cancelToast()
            }, durationTime())
        }
    }
}

private fun Context.showToast(@StringRes messageId: Int, duration: Int): Toast {
    return showToast(getString(messageId), duration)
}

/**
 * 取消Toast，要将toast、firstShowTime和nextShowTime都初始化
 */
private fun cancelToast() {
    toast?.cancel()
    toast = null
    firstShowTime = 0L
    nextShowTime = 0L
}

fun Context.shortToast(message: CharSequence) = showToast(message, Toast.LENGTH_SHORT)

fun Context.shortToast(@StringRes messageId: Int) = showToast(messageId, Toast.LENGTH_SHORT)

fun Context.longToast(message: CharSequence) = showToast(message, Toast.LENGTH_LONG)

fun Context.longToast(@StringRes messageId: Int) = showToast(messageId, Toast.LENGTH_LONG)

