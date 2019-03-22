package com.lindroid.androidutilskt.extension.logcat

import com.lindroid.androidutilskt.extension.logcat.formatstrategy.AndroidFormatStrategy
import com.lindroid.androidutilskt.extension.logcat.logadapter.AndroidLogAdapter
import com.lindroid.androidutilskt.extension.logcat.logstrategy.LogcatLogStrategy

/**
 * @author Lin
 * @date 2019/3/22
 * @function 日志工具配置
 * @Description
 */
class LogConfig constructor() {

    internal var isLoggable = true
    internal var methodCount = 1
    internal var methodOffset = 0
    internal var isShowThread = true
    internal var isShowGlobalTag = true
    internal var isShowBorder = true
    internal var logStrategy: LogcatLogStrategy = LogcatLogStrategy()
    internal var tag: String? = "LogUtil"

    constructor(init: (LogConfig.() -> Unit)? = null) : this() {
        init?.run {
            this()
            build()
        }
    }

    fun build() {
        addLogAdapter(object : AndroidLogAdapter(createFormatStrategy()) {
            override fun isLoggable(level: Int, tag: String?) = isLoggable
        })
    }

    fun createFormatStrategy() = AndroidFormatStrategy(this)

    fun setLogEnable(isLoggable: Boolean) = this.apply { this.isLoggable = isLoggable }

    fun setMethodCount(count: Int) = this.apply { methodCount = count }

    fun setMethodOffset(offset: Int) = this.apply { methodOffset = offset }

    fun setShowThread(isShow: Boolean) = this.apply { isShowThread = isShow }

    fun setShowGlobalTag(isShow: Boolean) = this.apply { isShowGlobalTag = isShow }

    fun setLogStrategy(logStrategy: LogcatLogStrategy) = this.apply { this.logStrategy = logStrategy }

    fun setShowBorder(isShowBorder: Boolean) = this.apply { this.isShowBorder = isShowBorder }

    fun setTag(tag: String?) = this.apply { this.tag = tag }

}