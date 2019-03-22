package com.lindroid.androidutilskt.extension.logcat.logadapter

import com.lindroid.androidutilskt.extension.logcat.LogLevel
import com.lindroid.androidutilskt.extension.logcat.formatstrategy.AndroidFormatStrategy
import com.lindroid.androidutilskt.extension.logcat.formatstrategy.FormatStrategy

/**
 * @author Lin
 * @date 2019/3/20
 * @function
 * @Description
 */
open class AndroidLogAdapter : LogAdapter {

    constructor()

    constructor(formatStrategy: FormatStrategy) {
        this.formatStrategy = formatStrategy
    }

    //    private var formatStrategy: FormatStrategy = AndroidFormatStrategy.newBuilder().build()
    private var formatStrategy: FormatStrategy = AndroidFormatStrategy.newBuilder().createFormatStrategy()

    /**
     * 设置是否打印日志
     */
    override fun isLoggable(@LogLevel level: Int, tag: String?) = true

    override fun log(@LogLevel level: Int, tag: String?, message: String) {
        formatStrategy.log(level, tag, message)
    }
}