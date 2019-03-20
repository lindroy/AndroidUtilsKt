package com.lindroid.androidutilskt.extension.logcat

/**
 * @author Lin
 * @date 2019/3/20
 * @function
 * @Description
 */
class AndroidLogAdapter : LogAdapter {

    constructor()

    constructor(formatStrategy: FormatStrategy) {
        this.formatStrategy = formatStrategy
    }

    private var formatStrategy: FormatStrategy = AndroidFormatStrategy.build()

    /**
     * 设置是否打印日志
     */
    override fun isLoggable(@LogLevel level: Int, tag: String?) = true

    override fun log(@LogLevel level: Int, tag: String?, message: String) {
        formatStrategy.log(level, tag, message)
    }
}