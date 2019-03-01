package com.lindroid.androidutilskt.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * @author Lin
 * @date 2018/10/18
 * @function SharedPreferences工具类
 * @Description
 * 1、get和put方法均是成对的，且put方法会返回布尔值用于判断是否存储成功；
 * 2、key的命名建议以SK或者SP_KEY作为前缀；
 * 3、表的命名建议以sp_file作为前缀。
 */

/**默认存储的文件名**/
private var defFileName = "sp_util"

private fun Context.getSp(fileName: String = defFileName) = getSharedPreferences(fileName, MODE_PRIVATE)

internal fun setSpDefaultFile(fileName: String) {
    defFileName = fileName
}

fun Context.putSpString(key: String, value: String, fileName: String = defFileName): Boolean =
    getSp(fileName).edit().putString(key, value).commit()

fun Context.getSpString(key: String, defValue: String = "", fileName: String = defFileName): String =
    getSp(fileName).getString(key, defValue) ?: ""

fun Context.putSpBoolean(key: String, value: Boolean, fileName: String = defFileName): Boolean =
    getSp(fileName).edit().putBoolean(key, value).commit()

fun Context.getSpBoolean(key: String, defValue: Boolean = false, fileName: String = defFileName): Boolean =
    getSp(fileName).getBoolean(key, defValue)

fun Context.putSpStrSet(key: String, value: Set<String>, fileName: String = defFileName): Boolean =
    getSp(fileName).edit().putStringSet(key, value).commit()

fun Context.getSpStrSet(key: String, defValue: Set<String>, fileName: String = defFileName): Set<String> =
    getSp(fileName).getStringSet(key, defValue) ?: setOf()

fun Context.putSpInt(key: String, value: Int, fileName: String = defFileName): Boolean =
    getSp(fileName).edit().putInt(key, value).commit()

fun Context.getSpInt(key: String, defValue: Int = 0, fileName: String = defFileName): Int =
    getSp(fileName).getInt(key, defValue)

fun Context.putSpLong(key: String, value: Long, fileName: String = defFileName): Boolean =
    getSp(fileName).edit().putLong(key, value).commit()

fun Context.getSpLong(key: String, defValue: Long = 0L, fileName: String = defFileName): Long =
    getSp(fileName).getLong(key, defValue)

fun Context.putSpFloat(key: String, value: Float, fileName: String = defFileName): Boolean =
    getSp(fileName).edit().putFloat(key, value).commit()

fun Context.getSpFloat(key: String, defValue: Float = 0F, fileName: String = defFileName): Float =
    getSp(fileName).getFloat(key, defValue)

/**
 * 保存long、float和int数据，double除外
 * putSpNumber方法的作用跟putSpInt、putSpLong和putSpFloat的作用是一样的
 */
@SuppressLint("ApplySharedPref")
fun Context.putSpNumber(key: String, value: Number, fileName: String = defFileName): Boolean {
    val editor = getSp(fileName).edit()
    return when (value) {
        is Int -> editor.putInt(key, value).commit()
        is Float -> editor.putFloat(key, value).commit()
        is Long -> editor.putLong(key, value).commit()
        else -> {
            false
        }
    }
}

/**
 * 获取数值型数据
 * @param defValue 重要：根据默认值来判断数据类型，如果是double的话就转为float
 */
fun Context.getSpNumber(key: String, defValue: Number, fileName: String = defFileName): Number {
    val preference = getSp(fileName)
    return when (defValue) {
        is Int -> preference.getInt(key, defValue)
        is Float -> preference.getFloat(key, defValue)
        is Long -> preference.getLong(key, defValue)
        is Double -> preference.getFloat(key, defValue.toFloat())
        else -> {
            defValue
        }
    }
}

/**
 * 删除某条数据
 */
fun Context.deleteSpKey(key: String, fileName: String = defFileName): Boolean =
    getSp(fileName).edit().remove(key).commit()

/**
 * 清除SharedPreferences中的数据
 * @param fileName ：默认清除defFileName的数据，也可以输入其他的表名
 */
fun Context.clearSp(fileName: String = defFileName): Boolean =
    getSp(fileName).edit().clear().commit()

