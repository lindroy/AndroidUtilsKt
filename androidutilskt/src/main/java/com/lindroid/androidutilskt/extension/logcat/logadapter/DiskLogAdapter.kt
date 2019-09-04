package com.lindroid.androidutilskt.extension.logcat.logadapter

import com.lindroid.androidutilskt.extension.logcat.formatstrategy.CsvFormatStrategy
import com.lindroid.androidutilskt.extension.logcat.formatstrategy.FormatStrategy

/**
 * @author Lin
 * @date 2019/9/3
 * @function 保存日志到磁盘
 * @Description
 */
open class DiskLogAdapter : LogAdapter {

    private var formatStrategy: FormatStrategy = CsvFormatStrategy.newBuilder().createFormatStrategy()

    constructor(formatStrategy: CsvFormatStrategy) {
        this.formatStrategy = formatStrategy
    }

    /**
     * 设置是否打印日志
     */
    override fun isLoggable(level: Int, tag: String?) = true

    /**
     * 所有日志的打印通道
     */
    override fun log(level: Int, tag: String?, message: String) {
        formatStrategy.log(level, tag, message)
    }

    /**
     * 是否是临时的设置
     */
    override fun isTempAdapter() = false
}