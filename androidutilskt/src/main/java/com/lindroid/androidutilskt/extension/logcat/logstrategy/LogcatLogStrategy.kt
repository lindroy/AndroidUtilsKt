package com.lindroid.androidutilskt.extension.logcat.logstrategy

import android.util.Log
import com.lindroid.androidutilskt.extension.logcat.LogLevel

/**
 * @author Lin
 * @date 2019/3/21
 * @function 日志策略
 * @Description
 */
private const val DEFAULT_TAG = "DEFAULT_TAG"

class LogcatLogStrategy : LogStrategy {

    override fun log(@LogLevel level: Int, tag: String?, message: String) {
        Log.println(level, tag ?: DEFAULT_TAG, message)
    }
}