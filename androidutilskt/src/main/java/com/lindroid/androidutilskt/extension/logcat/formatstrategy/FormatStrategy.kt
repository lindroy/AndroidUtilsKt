package com.lindroid.androidutilskt.extension.logcat.formatstrategy

import com.lindroid.androidutilskt.extension.logcat.LogLevel

/**
 * @author Lin
 * @date 2019/3/19
 * @function 设置日志打印格式接口
 * @Description
 */

interface FormatStrategy {
    fun log(@LogLevel level: Int, tag: String?, message: String)
}