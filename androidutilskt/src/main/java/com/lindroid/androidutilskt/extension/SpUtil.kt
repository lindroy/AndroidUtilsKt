@file:JvmName("SpUtil")
package com.lindroid.androidutilskt.extension

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import com.lindroid.androidutilskt.app.AndUtil

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

private fun getShare(fileName: String = defFileName) = AndUtil.appContext.getSharedPreferences(fileName, MODE_PRIVATE)

internal fun setSpDefaultFile(fileName: String) {
    defFileName = fileName
}

@JvmOverloads
fun putSpString(key: String, value: String, fileName: String = defFileName): Boolean =
    getShare(fileName).edit().putString(key, value).commit()

@JvmOverloads
fun getShareString(key: String, defValue: String = "", fileName: String = defFileName): String =
    getShare(fileName).getString(key, defValue) ?: ""

@JvmOverloads
fun putSpBoolean(key: String, value: Boolean, fileName: String = defFileName): Boolean =
    getShare(fileName).edit().putBoolean(key, value).commit()

@JvmOverloads
fun getSpBoolean(key: String, defValue: Boolean = false, fileName: String = defFileName): Boolean =
    getShare(fileName).getBoolean(key, defValue)

@JvmOverloads
fun putSpStrSet(key: String, value: Set<String>, fileName: String = defFileName): Boolean =
    getShare(fileName).edit().putStringSet(key, value).commit()

@JvmOverloads
fun getSpStrSet(key: String, defValue: Set<String>, fileName: String = defFileName): Set<String> =
    getShare(fileName).getStringSet(key, defValue) ?: setOf()

@JvmOverloads
fun putSpInt(key: String, value: Int, fileName: String = defFileName): Boolean =
    getShare(fileName).edit().putInt(key, value).commit()

@JvmOverloads
fun getSpInt(key: String, defValue: Int = 0, fileName: String = defFileName): Int =
    getShare(fileName).getInt(key, defValue)

@JvmOverloads
fun putSpLong(key: String, value: Long, fileName: String = defFileName): Boolean =
    getShare(fileName).edit().putLong(key, value).commit()

@JvmOverloads
fun getSpLong(key: String, defValue: Long = 0L, fileName: String = defFileName): Long =
    getShare(fileName).getLong(key, defValue)

@JvmOverloads
fun putSpFloat(key: String, value: Float, fileName: String = defFileName): Boolean =
    getShare(fileName).edit().putFloat(key, value).commit()

@JvmOverloads
fun getSpFloat(key: String, defValue: Float = 0F, fileName: String = defFileName): Float =
    getShare(fileName).getFloat(key, defValue)


/**
 * 保存数据，数据类型由传入的值确定
 * @throws IllegalArgumentException:数据类型不属于SharedPreferences能保存的类型
 */
@JvmOverloads
@SuppressLint("ApplySharedPref")
fun <T : Any> putSp(key: String, value: T, fileName: String = defFileName): Boolean =
    with(getShare(fileName).edit()) {
        when (value) {
            is Int -> putInt(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
            is Double -> putFloat(key, value.toFloat())
            is Boolean -> putBoolean(key, value)
            is String -> putString(key, value)
            else -> throw IllegalArgumentException("This type can not be saved into SharedPreferences!")
        }.commit()
    }

/**
 * 取出数据，数据类型由传入的默认值确定
 * @throws IllegalArgumentException:数据类型不属于SharedPreferences能保存的类型
 */
@Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
fun <T : Any> getSp(key: String, defValue: T, fileName: String = defFileName): T = with(getShare(fileName)) {
    when (defValue) {
        is Int -> getInt(key, defValue)
        is Float -> getFloat(key, defValue)
        is Long -> getLong(key, defValue)
        is String -> getString(key, defValue)
        is Boolean -> getBoolean(key, defValue)
        is Double -> getFloat(key, defValue.toFloat())
        else -> throw IllegalArgumentException("This type can not be found in SharedPreferences!")
    } as T
}

/**
 * 删除某条数据
 */
@JvmOverloads
fun deleteSpKey(key: String, fileName: String = defFileName): Boolean =
    getShare(fileName).edit().remove(key).commit()

/**
 * 清除SharedPreferences中的数据
 * @param fileName ：默认清除defFileName的数据，也可以输入其他的表名
 */
@JvmOverloads
fun clearSp(fileName: String = defFileName): Boolean =
    getShare(fileName).edit().clear().commit()



