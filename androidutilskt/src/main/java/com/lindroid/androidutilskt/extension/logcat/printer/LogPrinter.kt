package com.lindroid.androidutilskt.extension.logcat.printer

import com.lindroid.androidutilskt.extension.logcat.*
import com.lindroid.androidutilskt.extension.logcat.logadapter.LogAdapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.PrintWriter
import java.io.StringReader
import java.io.StringWriter
import java.net.UnknownHostException
import java.util.*
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 * @author Lin
 * @date 2019/3/19
 * @function 准备要打印的日志信息
 * @Description
 */
class LogPrinter : Printer {
    /**
     * 重置成全局设置
     */
    override fun resetLogAdapter() {
        if (logAdapters.isNotEmpty()) {
            val logAdapter = logAdapters[0]
            logAdapters.clear()
            logAdapters.add(logAdapter)
        }
    }

    private val logAdapters: ArrayList<LogAdapter> = ArrayList()

    override fun d(tag: String?, content: Any?) {
        logContent(DEBUG, tag, transToString(content))
    }

    override fun v(tag: String?, message: String?, vararg args: Any?) {
        logContent(VERBOSE, tag, message, args = *args)
    }

    override fun i(tag: String?, message: String?, vararg args: Any?) {
        logContent(INFO, tag, message, args = *args)
    }

    override fun d(tag: String?, message: String?, vararg args: Any?) {
        logContent(DEBUG, tag, message, args = *args)
    }

    override fun w(tag: String?, message: String?, vararg args: Any?) {
        logContent(WARN, tag, message, args = *args)
    }

    override fun e(tag: String?, throwable: Throwable?, message: String?, vararg args: Any?) {
        logContent(ERROR, tag, message, throwable, *args)
    }

    override fun wtf(tag: String?, message: String?, vararg args: Any?) {
        logContent(ASSERT, tag, message, args = *args)
    }

    override fun json(tag: String?, json: String?) {
        if (json.isNullOrEmpty()) {
            d(tag, "Empty/Null json content")
            return
        }
        try {
            if (json.trim().startsWith("{")) {
                d(tag, JSONObject(json.trim()).toString(2))
                return
            }
            if (json.trim().startsWith("[")) {
                d(tag, JSONArray(json.trim()).toString(2))
                return
            }
            e(null, message = "Invalid Json")
        } catch (e: JSONException) {
            e(null, message = "Invalid Json")
        }
    }

    override fun xml(tag: String?, xml: String?) {
        if (xml.isNullOrEmpty()) {
            d(tag, "Empty/Null xml content")
            return
        }
        try {
            val xmlInput = StreamSource(StringReader(xml))
            val xmlOutput = StreamResult(StringWriter())
            TransformerFactory.newInstance().newTransformer().apply {
                setOutputProperty(OutputKeys.INDENT, "yes")
                setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
                transform(xmlInput, xmlOutput)
            }
            d(tag, xmlOutput.writer.toString().replaceFirst(">", ">\n"))
        } catch (e: TransformerException) {
            e(null, message = "Invalid xml")
        }
    }

    @Synchronized
    override fun log(level: Int, tag: String?, message: String?, throwable: Throwable?) {
        var msg = message
        if (throwable != null && message != null) {
            msg += (" : ${getStackTraceString(throwable)}")
        }
        if (throwable != null && message == null) {
            msg = getStackTraceString(throwable)
        }
        if (msg.isNullOrEmpty()) {
            msg = "Empty/NULL log message"
        }

        /* logAdapters.forEach {
             if (it.isLoggable(level, tag)) {
                 it.log(level, tag, msg)
             }
         }*/

        //只按照最后一次的设置打印日志
        if (logAdapters.isNotEmpty()) {
            logAdapters.last().apply {
                if (isLoggable(level, tag)) {
                    log(level, tag, msg)
                }
                if (isTempAdapter()) {
                    logAdapters.remove(this)
                }
            }
        }
    }

    override fun addAdapter(adapter: LogAdapter) {
        logAdapters.add(adapter)
    }

    override fun clearLogAdapters() {
        if (logAdapters.isNotEmpty()) {
            logAdapters.clear()
        }
    }

    @Synchronized
    private fun logContent(
        level: Int,
        tag: String?,
        message: String?,
        throwable: Throwable? = null,
        vararg args: Any?
    ) {
        fun createMessage() = if (message == null) {
            "null"
        } else {
            if (args.isEmpty()) message else String.format(message, *args)
        }
        log(level, tag, createMessage(), throwable)
    }

    private fun getStackTraceString(throwable: Throwable?): String {
        when (throwable) {
            null -> return ""
            else -> {
                var tr = throwable
                while (tr != null) {
                    if (tr is UnknownHostException) {
                        return ""
                    }
                    tr = tr.cause
                }
                val sw = StringWriter()
                val pw = PrintWriter(sw)
                throwable.printStackTrace(pw)
                pw.flush()
                return sw.toString()
            }
        }
    }

    fun transToString(content: Any?): String {
        return when {
            content == null -> "null"
            !(this.javaClass.isArray) -> {
                this.toString()
            }
            content is BooleanArray -> Arrays.toString(content)
            content is CharArray -> Arrays.toString(content)
            content is ByteArray -> Arrays.toString(content)
            content is ShortArray -> Arrays.toString(content)
            content is IntArray -> Arrays.toString(content)
            content is LongArray -> Arrays.toString(content)
            content is FloatArray -> Arrays.toString(content)
            content is DoubleArray -> Arrays.toString(content)
            content is Array<*> -> Arrays.deepToString(content)
            else -> "Couldn't find a correct type for the object"
        }
    }


}