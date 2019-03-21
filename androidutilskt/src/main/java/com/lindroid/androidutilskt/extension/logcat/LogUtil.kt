@file:JvmName("LogUtil")

//此处的文件名修改后需要到com.lindroid.androidutilskt.extension.logcat.AndroidFormatStrategy.getStackOffset方法中同步修改


package com.lindroid.androidutilskt.extension.logcat

/**
 * @author Lin
 * @date 2019/3/19
 * @function 日志工具类
 * @Description
 */

private val printer: LogPrinter by lazy {
    LogPrinter()
}

fun addLogAdapter(adapter: LogAdapter) {
    printer.addAdapter(adapter)
}

fun String.d(vararg args: Any?) {
    printer.d(null, this, *args)
}

fun String.dt(tag: String, vararg args: Any?) {
    printer.d(tag, this, *args)
}

fun Any?.d() {
    printer.d(null, content = this)
}

fun Any?.dt(tag: String) {
    printer.d(tag, content = this)
}

fun String.v(vararg args: Any?) {
    printer.v(null, this, * args)
}

fun String.vt(tag: String, vararg args: Any?) {
    printer.v(tag, this, args)
}

fun String.i(vararg args: Any?) {
    printer.i(null, this, *args)
}

fun String.it(tag: String, vararg args: Any?) {
    printer.i(tag, this, *args)
}

fun String.w(vararg args: Any?) {
    printer.w(null, this, *args)
}

fun String.wt(tag: String, vararg args: Any?) {
    printer.w(tag, this, *args)
}

fun String.e(vararg args: Any?) {
    printer.e(null, null, this, *args)
}

fun String.e(throwable: Throwable?, vararg args: Any?) {
    printer.e(null, throwable, this, *args)
}

fun String.et(tag: String, vararg args: Any?) {
    printer.e(tag, null, this, *args)
}

fun String.et(tag: String, throwable: Throwable?, vararg args: Any?) {
    printer.e(tag, null, this, *args)
}

/**
 * 打印格式化的json
 */
fun String?.json(tag: String? = null) {
    printer.json(null, this)
}

/**
 * 打印格式化的xml
 */
fun String?.xml(tag: String? = null) {
    printer.xml(null, this)
}


