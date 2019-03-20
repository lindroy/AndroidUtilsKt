@file:JvmName("LogUtil")

package com.lindroid.androidutilskt.extension.logcat

/**
 * @author Lin
 * @date 2019/3/19
 * @function 日志工具类
 * @Description
 */
private val printer = LogPrinter()

fun String.d(vararg args: Any) {
    printer.d(null, this, args)
}

fun String.dt(tag: String, vararg args: Any) {
    printer.d(tag, this, args)
}

fun Any?.d() {
    printer.d(null, content = this)
}

fun Any?.dt(tag: String) {
    printer.d(tag, content = this)
}

