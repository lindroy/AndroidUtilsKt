package com.lindroid.androidutilskt.extension

import android.util.Log
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Lin
 * @date 2018/10/29
 * @function 时间日期工具类
 * @Description
 */

const val FORMAT_YMDHM = "yyyy-MM-dd HH:mm"
const val FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss"
const val FORMAT_YMD = "yyyy-MM-dd"
const val FORMAT_YMD_CHINESE = "yyyy年MM月dd日"
const val FORMAT_HM = "HH:mm"
private const val TAG = "TimeUtil"

/**服务器返回的时间格式**/
internal var serverFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS"

/**获取当前年份**/
val currentYear: Int
    get() = Calendar.getInstance().get(Calendar.YEAR)

/**获取当前月份**/
val currentMonth
    get() = Calendar.getInstance().get(Calendar.MONTH) + 1


/**获取当前日**/
val currentDay: Int
    get() = Calendar.getInstance().get(Calendar.DATE)


/**获取当前时间戳**/
val currentTimeMillis: Long
    get() = System.currentTimeMillis()

/**
 * 获取当前日期，默认格式为"yyyy-MM-dd"
 */
fun formatCurrentDate(format: String = FORMAT_YMD) = getTimeFormatStr(format)

/**
 * 获取当前时间，格式为"yyyy-MM-dd HH:mm"
 */
fun formatCurrentDateTime(format: String = FORMAT_YMDHM) = getTimeFormatStr(format)

/**
 * 获取当前时间，格式为"HH:mm"
 */
fun formatCurrentTime(format: String = FORMAT_HM) = getTimeFormatStr(format)

/**
 * 将服务器时间格式转换为年月日
 */
fun String.formatTimeYMD(pattern: String = serverFormat): String =
    formatTimeWithPattern(FORMAT_YMD, pattern)

/**
 * 将服务器时间格式转换为年月日（帶漢字）
 */
fun String.formatTimeYMDChinese(pattern: String = serverFormat): String =
    formatTimeWithPattern(FORMAT_YMD_CHINESE, pattern)

/**
 * 将服务器时间格式转换为时分
 */
fun String.formatTimeHM(pattern: String = serverFormat): String = formatTimeWithPattern(FORMAT_HM, pattern)

/**
 * 将服务器时间格式转换为年月日时分
 */
fun String.formatTimeYMDHM(pattern: String = serverFormat): String = formatTimeWithPattern(FORMAT_YMDHM, pattern)

/**
 * 将服务器时间格式转换为年月日时分秒
 */
fun String.formatTimeYMDHMS(pattern: String = serverFormat): String =
    formatTimeWithPattern(FORMAT_YMDHMS, pattern)

/**
 * @param target 转换后的时间格式
 * @param source 转换前的时间格式，一般以服务器为准
 * @return 失败是返回空字符串
 */
private fun String.formatTimeWithPattern(target: String, source: String = serverFormat): String {
    val formatter = SimpleDateFormat(source, Locale.getDefault())
    return try {
        val date = formatter.parse(this, ParsePosition(0))
        SimpleDateFormat(target, Locale.getDefault()).format(date)
    } catch (e: Exception) {
        Log.e(TAG, "日期转换错误：$this")
        ""
//        App.instance.getString(R.string.empty_result)
    }
}

private fun getTimeFormatStr(format: String, original: Any = Date()) =
    SimpleDateFormat(format, Locale.getDefault()).format(original)
        ?: ""


private const val ONE_MINUTE = 60000L
private const val ONE_HOUR = 3600000L

/**
 * 获取当前时间与返回时间的比较值
 *//*
fun String.formatRelativeTime(): String {
    fun Long.toSecond() = this / 1000
    fun Long.toMinutes() = this.toSecond() / 60L
    fun Long.toHour() = this.toMinutes() / 60L
    val date = SimpleDateFormat(serverFormat, Locale.getDefault()).parse(this)
    val delta = getCurTimeMillis() - date.time
    return when {
    //xx分钟前
        delta < 1L * ONE_HOUR -> {
            if (delta.toMinutes()<=1){
                //不足一分钟显示1分钟前
                "1${App.instance.getString(R.string.time_one_minute_ago)}"
            }else{
                "${delta.toMinutes()}${App.instance.getString(R.string.time_one_minute_ago)}"
            }
        }
    //xx小時前
        delta >= ONE_HOUR && delta < 24 * ONE_HOUR -> "${delta.toHour()}${App.instance.getString(R.string.time_one_hour_ago)}"
    //昨天
        delta >= 24 * ONE_HOUR && delta < 48 * ONE_HOUR -> App.instance.getString(R.string.time_yesterday)
    //前天
        delta >= 48 * ONE_HOUR && delta < 72 * ONE_HOUR -> App.instance.getString(R.string.time_day_before_yesterday)
    //顯示年月日
        delta >= 72 * ONE_HOUR -> this.formatTimeYMD()
        else ->this.formatTimeYMD()
    }
}*/
