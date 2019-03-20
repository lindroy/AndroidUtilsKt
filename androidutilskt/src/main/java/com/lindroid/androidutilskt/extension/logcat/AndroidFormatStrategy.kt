package com.lindroid.androidutilskt.extension.logcat

import android.util.Log

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

class AndroidFormatStrategy(builder: Builder) : FormatStrategy {
    private val methodCount by lazy { builder.methodCount }

    private val methodOffset by lazy { builder.methodOffset }

    private val isShowThread by lazy { builder.isShowThread }

    private val isShowGlobalTag by lazy { builder.isShowGlobalTag }

    private val logStrategy by lazy { builder.logStrategy }

    private var logTag: String? = builder.tag

    companion object {
        fun newBuilder() = Builder()
    }

    override fun log(@LogLevel level: Int, tag: String?, message: String) {
        if (!tag.isNullOrEmpty() && this.logTag != tag) {
            this.logTag = if (isShowGlobalTag) "${this.logTag}-tag" else tag
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
                if (methodCount > 0) {
                    logDivider(level, logTag)
                }
                var i = 0
                while (i < length) {
                    val count = Math.min(length - i, CHUNK_SIZE)
                    logContent(level, logTag, String(message.toByteArray(), i, count))
                    i += CHUNK_SIZE
                }
                logBottomBorder(level, logTag)
            }
        }
    }

    private fun logHeaderContent(logLevel: Int, tag: String?) {
        if (isShowThread) {
            logStrategy?.log(logLevel, tag, "$HORIZONTAL_LINE Thread: ${Thread.currentThread().name}")
            logDivider(logLevel, tag)
        }
        val trace: Array<StackTraceElement> = Thread.currentThread().stackTrace
        val stackOffset = getStackOffset(trace) + methodOffset
        var realMethodCount = 0
        if (methodCount + stackOffset > trace.size) {
            realMethodCount = trace.size - stackOffset - 1
        }

        for ((i, item) in (realMethodCount downTo 1).withIndex()) {
            val stackIndex = i + stackOffset
            if (stackIndex >= trace.size) {
                continue
            }
            val builder = with(StringBuilder()) {
                append(HORIZONTAL_LINE)
                append(' ')
                append(logLevel)
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
            logStrategy?.log(logLevel, tag, builder.toString())
        }
    }

    private fun logTopBorder(logLevel: Int, tag: String?) {
        logStrategy?.log(logLevel, tag, TOP_BORDER)
    }

    private fun logDivider(logLevel: Int, tag: String?) {
        logStrategy?.log(logLevel, tag, MIDDLE_BORDER)
    }

    private fun logBottomBorder(logLevel: Int, tag: String?) {
        logStrategy?.log(logLevel, tag, BOTTOM_BORDER)
    }

    private fun logContent(logLevel: Int, tag: String?, content: String) {
//        System.getProperty("line.separator")
        val lines = content.split(System.lineSeparator())
        lines.forEach {
            logStrategy?.log(logLevel, tag, "$HORIZONTAL_LINE $it")
        }
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
//                    && name != d()::class.java.name 会引起死循环，内存泄露
            if (name != LogPrinter::class.java.name) {
                return --i
            }
            i++
            Log.e("Tag", "i=$i")
        }
        return -1
    }

    private fun getSimpleClassName(name: String): String {
        val lastIndex = name.lastIndexOf(".")
        return name.substring(lastIndex + 1)
    }

    class Builder {
        var methodCount = 1
        var methodOffset = 3
        var isShowThread = true
        var isShowGlobalTag = false
        var logStrategy: LogStrategy? = null
        var tag: String? = "LogUtil"

        fun build(): AndroidFormatStrategy {
            if (logStrategy == null) {
                logStrategy = LogStrategy()
            }
            return AndroidFormatStrategy(this)
        }

        fun setMethodCount(count: Int) = this.apply { methodCount = count }

        fun setMethodOffset(offset: Int) = this.apply { methodOffset = offset }

        fun setShowThread(isShow: Boolean) = this.apply { isShowThread = isShow }

        fun setShowGlobalTag(isShow: Boolean) = this.apply { isShowGlobalTag = isShow }

        fun setLogStrategy(logStrategy: LogStrategy) = this.apply { this.logStrategy = logStrategy }

        fun setTag(tag: String?) = this.apply { this.tag = tag }
    }
}