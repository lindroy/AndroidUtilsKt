package com.lindroid.androidutilskt.extension.logcat.formatstrategy

import com.lindroid.androidutilskt.extension.logcat.DiskConfig
import com.lindroid.androidutilskt.extension.logcat.logLevel

/**
 * @author Lin
 * @date 2019/9/3
 * @function 格式化成CSV文件
 * @Description
 */
private val NEW_LINE = System.getProperty("line.separator")
private val NEW_LINE_REPLACEMENT = " <br> "
private val SEPARATOR = ","

class CsvFormatStrategy(private val builder: DiskConfig) : FormatStrategy {

    private val tag = builder.tag
    private val date = builder.date
    private val dateFormat = builder.dateFormat
    private val logStrategy = builder.logStrategy

    override fun log(level: Int, tag: String?, message: String) {
        val logTag = if (!tag.isNullOrEmpty() && this.tag != tag) "${this.tag}-$tag" else this.tag
        date.time = System.currentTimeMillis()
        val sb = with(StringBuilder()) {
            //供机器读取的时间
            append(date.time)
            //供人读取的时间
            append(SEPARATOR)
            append(dateFormat.format(date))
            //日志等级
            append(SEPARATOR)
            append(logLevel(level))
            //tag
            append(SEPARATOR)
            append(logTag)
            val msg = if (message.contains(NEW_LINE)) {
                //换行符会破坏CSV格式，需要替换掉
                message.replace(NEW_LINE, NEW_LINE_REPLACEMENT)
            } else message
            append(SEPARATOR)
            append(msg)
            append(NEW_LINE)
            this
        }
        logStrategy?.log(level, logTag, sb.toString())
    }

    companion object {
        fun newBuilder() = DiskConfig()
    }
}