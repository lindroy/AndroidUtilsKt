@file:JvmName("LogUtil")

//此处的文件名修改后需要到com.lindroid.androidutilskt.extension.logcat.AndroidFormatStrategy.getStackOffset方法中同步修改

package com.lindroid.androidutilskt.extension.logcat

import com.lindroid.androidutilskt.extension.logcat.logadapter.LogAdapter
import com.lindroid.androidutilskt.extension.logcat.printer.LogPrinter

/**
 * @author Lin
 * @date 2019/3/19
 * @function 日志工具类
 * @Description
 */

private val printer: LogPrinter by lazy {
    LogPrinter()
}

/**
 * 初始化LogUtil
 */
internal fun addLogAdapter(adapter: LogAdapter) {
    printer.addAdapter(adapter)
}

/**
 * 清除所有的配置
 */
fun clearLogConfigs() {
    printer.clearLogAdapters()
}

/**
 * 重置LogUtil配置
 */
fun resetLogConfig() {
    printer.resetLogAdapter()
}

/**
 * 打印Debug日志，仅支持String?
 * @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.d(vararg args: Any?) {
    printer.d(null, this, *args)
}

/**
 * 打印临时tag的Debug日志
 * @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.dt(tag: String, vararg args: Any?) {
    printer.d(tag, this, *args)
}

/**
 * 打印Debug日志
 * @receiver : 支持如下类型：List、Array、Set和Map
 */
fun Any?.d() {
    printer.d(null, content = this)
}

/**
 * 打印临时性Tag的Debug日志
 * @receiver : 支持如下类型：List、Array、Set和Map
 */
fun Any?.dt(tag: String) {
    printer.d(tag, content = this)
}

/**
 *  打印Verbose日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.v(vararg args: Any?) {
    printer.v(null, this, * args)
}

/**
 *  打印带临时性tag的Verbose日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.vt(tag: String, vararg args: Any?) {
    printer.v(tag, this, args)
}

/**
 *  打印Info日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.i(vararg args: Any?) {
    printer.i(null, this, *args)
}

/**
 *  打印带tag的Info日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.it(tag: String, vararg args: Any?) {
    printer.i(tag, this, *args)
}

/**
 *  打印Warn日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.w(vararg args: Any?) {
    printer.w(null, this, *args)
}

/**
 *  打印带tag的Warn日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.wt(tag: String, vararg args: Any?) {
    printer.w(tag, this, *args)
}

/**
 *  打印Error日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.e(vararg args: Any?) {
    printer.e(null, null, this, *args)
}

/**
 *  打印带tag的Error日志
 *  @param throwable: 抛出的异常
 */
fun String?.e(throwable: Throwable?, vararg args: Any?) {
    printer.e(null, throwable, this, *args)
}

/**
 *  打印带临时性tag的Error日志
 */
fun String?.et(tag: String, vararg args: Any?) {
    printer.e(tag, null, this, *args)
}

/**
 *  打印带tag的Error日志
 *  @param throwable: 抛出的异常
 */
fun String?.et(tag: String, throwable: Throwable?, vararg args: Any?) {
    printer.e(tag, throwable, this, *args)
}

/**
 *  打印wtf日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.wtf(vararg args: Any?) {
    printer.wtf(null, this, *args)
}

/**
 *  打印带tag的wtf日志
 *  @receiver : 仅支持可空String，为null时打印“null”
 */
fun String?.wtft(tag: String, vararg args: Any?) {
    printer.wtf(tag, this, *args)
}

/**
 * 打印格式化的json
 */
fun String?.json(tag: String? = null) {
    printer.json(tag, this)
}

/**
 * 打印格式化的xml
 */
fun String?.xml(tag: String? = null) {
    printer.xml(tag, this)
}

/**
 * 设置LogUtil配置，会优先于AndUtil.setLogGlobalConfig()的全局设置，但不会覆盖它
 */
@JvmOverloads
fun buildLogConfig(init: (LogConfig.() -> Unit)? = null) = LogConfig(false, init)

/**
 * 设置临时性的LogUtil配置，打印一次之后就会失效
 */
@JvmOverloads
fun buildLogTempConfig(init: (LogConfig.() -> Unit)? = null) = LogConfig(true, init)
