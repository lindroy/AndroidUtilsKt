package com.lindroid.androidutilskt.extension.logcat

import android.util.Log


/**
 * @author Lin
 * @date 2019/3/19
 * @function 日志打印策略
 * @Description
 */
private const val DEFAULT_TAG = "DEFAULT_TAG"

class LogStrategy {

    fun log(@LogLevel level: Int, tag: String?, message: String) {
        Log.println(level, tag ?: DEFAULT_TAG, message)
    }
}