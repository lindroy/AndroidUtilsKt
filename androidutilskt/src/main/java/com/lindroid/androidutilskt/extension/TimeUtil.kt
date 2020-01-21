@file:JvmName("TimeUtil")

package com.lindroid.androidutilskt.extension

import android.util.Log
import java.text.ParseException
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
const val FORMAT_HMS = "HH:mm:ss"
private const val TAG = "TimeUtil"

/**服务器返回的时间格式**/
internal var serverFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS"

/**默认时区，与主机的设置一致**/
internal var defTimeZone = TimeZone.getDefault()

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
 * @param pattern:输出的格式
 */
fun formatCurrentDate(pattern: String = FORMAT_YMD) = getTimeFormatStr(pattern)

/**
 * 获取当前时间，默认格式为"yyyy-MM-dd HH:mm"
 * @param pattern:输出的格式
 */
fun formatCurrentDateTime(pattern: String = FORMAT_YMDHM) = getTimeFormatStr(pattern)

/**
 * 获取当前时间，格式为"HH:mm"
 * @param pattern:输出的格式
 */
fun formatCurrentTime(pattern: String = FORMAT_HM) = getTimeFormatStr(pattern)

/**
 * 将服务器时间格式转换为年
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeY(target: String = "yyyy", source: String = serverFormat) =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为月
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeM(target: String = "MM", source: String = serverFormat) =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为年月
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeYM(target: String = "yyyy-MM", source: String = serverFormat) =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为日，保持两位，不足两位时在前面补上0
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeDD(target: String = "dd", source: String = serverFormat) =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为日
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeD(target: String = "dd", source: String = serverFormat) =
    formatTimeDD(target, source).let {
        if (it.startsWith("0")) {
            it.replaceFirst("0", "")
        } else it
    }


/**
 * 将服务器时间格式转换为年月日
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeYMD(target: String = FORMAT_YMD, source: String = serverFormat): String =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为年月日（带汉字）
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeYMDChinese(
    target: String = FORMAT_YMD_CHINESE,
    source: String = serverFormat
): String =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为时分
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeHM(target: String = FORMAT_HM, source: String = serverFormat): String =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为时分秒
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeHMS(target: String = FORMAT_HMS, source: String = serverFormat): String =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为年月日时分
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeYMDHM(target: String = FORMAT_YMDHM, source: String = serverFormat): String =
    formatTimeWithPattern(target, source)

/**
 * 将服务器时间格式转换为年月日时分秒
 * @param target:输出的格式
 * @param source:源格式
 */
fun String.formatTimeYMDHMS(target: String = FORMAT_YMDHMS,source: String = serverFormat): String =
    formatTimeWithPattern(target, source)

/**
 * 根据服务器的时间判断星期
 * @return:0表示星期日，1~6依次表示星期一到星期六
 */
fun String.formatTimeWeek(timeZone: TimeZone = defTimeZone): Int {
    val formatter = SimpleDateFormat(FORMAT_YMD, Locale.getDefault())
    val cal = Calendar.getInstance(timeZone)
    return try {
        cal.time = formatter.parse(this)
        cal.get(Calendar.DAY_OF_WEEK) - 1
    } catch (e: ParseException) {
        Log.e(TAG, "星期转换错误：$this")
        -1
    }
}

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

private fun getTimeFormatStr(pattern: String, source: Any = Date()) =
    SimpleDateFormat(pattern, Locale.getDefault()).format(source)
        ?: ""


//private const val ONE_MINUTE = 60000L
//private const val ONE_HOUR = 3600000L

/**
 * 获取某个时间与当前时间的比较值
 *//*
fun String.formatRelativeTime(pattern: String = serverFormat): String {
    fun Long.toSecond() = this / 1000
    fun Long.toMinutes() = this.toSecond() / 60L
    fun Long.toHour() = this.toMinutes() / 60L
    val date = SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    val delta = currentTimeMillis - date.time
    return when {
        //xx分钟前
        delta < 1L * ONE_HOUR -> {
            if (delta.toMinutes() <= 1) {
                //不足一分钟显示1分钟前
                AndUtil.getString(R.string.time_one_minute_ago)
            } else {
                String.format(AndUtil.getString(R.string.time_minute_ago), delta.toMinutes())
//                "${delta.toMinutes()}${AndUtil.getString(R.string.time_one_minute_ago)}"
            }
        }
        //xx小時前
        delta >= ONE_HOUR && delta < 24 * ONE_HOUR ->
            String.format(AndUtil.getString(R.string.time_hour_ago), delta.toHour())
//            "${delta.toHour()}${AndUtil.getString(R.string.time_one_hour_ago)}"
        //昨天
        delta >= 24 * ONE_HOUR && delta < 48 * ONE_HOUR -> AndUtil.getString(R.string.time_yesterday)
        //前天
        delta >= 48 * ONE_HOUR && delta < 72 * ONE_HOUR -> AndUtil.getString(R.string.time_day_before_yesterday)
        //顯示年月日
        delta >= 72 * ONE_HOUR -> this.formatTimeYMD()
        else -> this.formatTimeYMD()
    }
}*/
