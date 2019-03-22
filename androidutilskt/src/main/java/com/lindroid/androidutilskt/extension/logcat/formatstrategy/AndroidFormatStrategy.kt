package com.lindroid.androidutilskt.extension.logcat.formatstrategy

import com.lindroid.androidutilskt.extension.logcat.LogConfig
import com.lindroid.androidutilskt.extension.logcat.LogLevel
import com.lindroid.androidutilskt.extension.logcat.logstrategy.LogcatLogStrategy
import com.lindroid.androidutilskt.extension.logcat.printer.LogPrinter

/**
 * @author Lin
 * @date 2019/3/20
 * @function Android打印日志的策略
 * @Description
 */

private const val CHUNK_SIZE = 4000

private const val MIN_STACK_OFFSET = 5

private const val TOP_LEFT_CORNER = '┌'
private const val BOTTOM_LEFT_CORNER = '└'
private const val MIDDLE_CORNER = '├'
private const val HORIZONTAL_LINE = '│'
private const val DOUBLE_DIVIDER = "────────────────────────────────────────────────────────"
private const val SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
private val TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
private val BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
private val MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER

class AndroidFormatStrategy(private val builder: LogConfig) : FormatStrategy {

    private val methodCount = builder.methodCount

    private val methodOffset by lazy { builder.methodOffset }

    private val isShowThread by lazy { builder.isShowThread }

    private val isShowGlobalTag by lazy { builder.isShowGlobalTag }

    private val isShowBorder = builder.isShowBorder

    private val logStrategy by lazy { builder.logStrategy }

    private var logTag: String? = builder.tag

    companion object {
        //        fun newBuilder() = Builder()
        fun newBuilder() = LogConfig()
    }

    override fun log(@LogLevel level: Int, tag: String?, message: String) {
        if (!tag.isNullOrEmpty() && this.logTag != tag) {
            this.logTag = if (isShowGlobalTag) "${this.logTag}-$tag" else tag
        }
        logTopBorder(level, logTag)
        logHeaderContent(level, logTag)

        val length = message.toByteArray().size
        when (length <= CHUNK_SIZE) {
            true -> {
                if (methodCount > 0) {
                    logDivider(level, logTag)
                }
                logContent(level, logTag, message)
                logBottomBorder(level, logTag)
            }
            false -> {
                if (
                    methodCount > 0) {
                    logDivider(level, logTag)
                }
                var i = 0
                while (i < length) {
                    val count = Math.min(
                        length - i,
                        CHUNK_SIZE
                    )
                    logContent(level, logTag, String(message.toByteArray(), i, count))
                    i += CHUNK_SIZE
                }
                logBottomBorder(level, logTag)
            }
        }
        //设置完一次性tag之后要用回默认的tag
        if (logTag != builder.tag) {
            logTag = builder.tag
        }
    }

    private fun logHeaderContent(logLevel: Int, tag: String?) {
        if (isShowThread) {
            logStrategy.log(
                logLevel,
                tag,
                "${if (isShowBorder) HORIZONTAL_LINE else ""} Thread: ${Thread.currentThread().name}"
            )
            logDivider(logLevel, tag)
        }
        val trace: Array<StackTraceElement> = Thread.currentThread().stackTrace
        val stackOffset = getStackOffset(trace) + methodOffset
        var realMethodCount = methodCount
        if (realMethodCount + stackOffset > trace.size) {
            realMethodCount = trace.size - stackOffset - 1
        }
        //在方法前面添加空格，层次更为分明
        var levelSpace = ""
        for (item in (realMethodCount downTo 1)) {
            val stackIndex = item + stackOffset
            if (stackIndex >= trace.size) {
                continue
            }
            val builder = with(StringBuilder()) {
                if (isShowBorder) {
                    append(HORIZONTAL_LINE)
                }
                append(' ')
                append(levelSpace)
                append(getSimpleClassName(trace[stackIndex].className))
                append(".")
                append(trace[stackIndex].methodName)
                append(" ")
                append(" (")
                append(trace[stackIndex].fileName)
                append(":")
                append(trace[stackIndex].lineNumber)
                append(")")
                this
            }
            levelSpace = "$levelSpace "
            logStrategy.log(logLevel, tag, builder.toString())
        }
    }

    private fun logTopBorder(logLevel: Int, tag: String?) {
        if (isShowBorder) {
            logStrategy.log(logLevel, tag, TOP_BORDER)
        }
    }

    private fun logDivider(logLevel: Int, tag: String?) {
        if (isShowBorder) {
            logStrategy.log(logLevel, tag, MIDDLE_BORDER)
        }
    }

    private fun logBottomBorder(logLevel: Int, tag: String?) {
        if (isShowBorder) {
            logStrategy.log(logLevel, tag, BOTTOM_BORDER)
        }
    }

    private fun logContent(logLevel: Int, tag: String?, content: String) {
//        System.getProperty("line.separator")
        val lines = content.split(System.lineSeparator())
        lines.forEach {
            logStrategy.log(logLevel, tag, "${if (isShowBorder) HORIZONTAL_LINE else ""} $it")
        }
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            if (e.className != LogPrinter::class.java.name && !e.fileName.contains("LogUtil")) {
                return --i
            }
            i++
        }
        return -1
    }

    private fun getSimpleClassName(name: String): String {
        val lastIndex = name.lastIndexOf(".")
        return name.substring(lastIndex + 1)
    }

    class Builder {
        internal var methodCount = 1
        internal var methodOffset = 0
        internal var isShowThread = true
        internal var isShowGlobalTag = true
        internal var isShowBorder = true
        internal var logStrategy: LogcatLogStrategy? = null
        internal var tag: String? = "LogUtil"

        constructor()

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

//        fun build(): AndroidFormatStrategy {
//            if (logStrategy == null) {
//                logStrategy = LogcatLogStrategy()
//            }
//            return AndroidFormatStrategy(this)
//        }

        fun setMethodCount(count: Int) = this.apply { methodCount = count }

        fun setMethodOffset(offset: Int) = this.apply { methodOffset = offset }

        fun setShowThread(isShow: Boolean) = this.apply { isShowThread = isShow }

        fun setShowGlobalTag(isShow: Boolean) = this.apply { isShowGlobalTag = isShow }

        fun setLogStrategy(logStrategy: LogcatLogStrategy) = this.apply { this.logStrategy = logStrategy }

        fun setTag(tag: String?) = this.apply { this.tag = tag }
    }

}

