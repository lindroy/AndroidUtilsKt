package com.lindroid.androidutilskt.extension.logcat

import com.lindroid.androidutilskt.extension.logcat.formatstrategy.AndroidFormatStrategy
import com.lindroid.androidutilskt.extension.logcat.logadapter.AndroidLogAdapter
import com.lindroid.androidutilskt.extension.logcat.logstrategy.LogcatLogStrategy

/**
 * @author Lin
 * @date 2019/3/22
 * @function 日志工具配置类
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
    private val init: (LogConfig.() -> Unit)? = null
    /**
     * 是否是临时性的设置
     */
    private var isTempConfig = false

    constructor(isTempConfig: Boolean, init: (LogConfig.() -> Unit)? = null) : this() {
        init?.run {
            this()
            this@LogConfig.isTempConfig = isTempConfig
            addLogConfig()
        }
    }

    /**
     * 如果是以Lambda的形式设置，则可以不必调用build()方法
     */
    fun build() {
        if (init == null) {
            addLogConfig()
        }
    }

    private fun addLogConfig() {
        addLogAdapter(object : AndroidLogAdapter(createFormatStrategy()) {
            override fun isLoggable(level: Int, tag: String?) = isLoggable
            override fun isTempAdapter() = isTempConfig
        })
    }

    internal fun createFormatStrategy() = AndroidFormatStrategy(this)

    /**
     * 设置是否显示日志
     */
    fun setLogEnable(isLoggable: Boolean) = this.apply { this.isLoggable = isLoggable }

    /**
     * 设置显示的方法数，默认为1
     */
    fun setMethodCount(count: Int) = this.apply { methodCount = count }

    /**
     * 设置栈偏移量，默认为0
     */
    fun setMethodOffset(offset: Int) = this.apply { methodOffset = offset }

    /**
     * 设置是否显示线程
     */
    fun setShowThread(isShow: Boolean) = this.apply { isShowThread = isShow }

    /**
     * 设置是否在临时Tag前面显示全局Tag
     */
    fun setShowGlobalTag(isShow: Boolean) = this.apply { isShowGlobalTag = isShow }

    /**
     * 自定义日志打印策略
     */
    fun setLogStrategy(logStrategy: LogcatLogStrategy) = this.apply { this.logStrategy = logStrategy }

    /**
     * 是否显示边框，默认显示，为了美观，建议显示
     */
    fun setShowBorder(isShowBorder: Boolean) = this.apply { this.isShowBorder = isShowBorder }

    /**
     * 设置默认的全局Tag，默认为LogUtil
     */
    fun setTag(tag: String?) = this.apply { this.tag = tag }

}