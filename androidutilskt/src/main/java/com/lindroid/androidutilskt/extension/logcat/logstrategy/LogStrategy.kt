package com.lindroid.androidutilskt.extension.logcat.logstrategy

import com.lindroid.androidutilskt.extension.logcat.LogLevel


/**
 * @author Lin
 * @date 2019/3/19
 * @function Logcat日志打印策略
 * @Description
 */


interface LogStrategy {
    fun log(@LogLevel level: Int, tag: String?, message: String)

}