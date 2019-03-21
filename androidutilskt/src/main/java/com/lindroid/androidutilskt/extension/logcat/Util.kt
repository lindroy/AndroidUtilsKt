package com.lindroid.androidutilskt.extension.logcat

import java.util.*


/**
 * @author Lin
 * @date 2019/3/20
 * @function LogUtil使用的工具类
 * @Description
 */
internal fun Any?.transToString(): String {
    return when {
        this == null -> "null"
        !(this.javaClass.isArray) -> {
            this.toString()
        }
        this is BooleanArray -> Arrays.toString(this)
        this is CharArray -> Arrays.toString(this)
        this is ByteArray -> Arrays.toString(this)
        this is ShortArray -> Arrays.toString(this)
        this is IntArray -> Arrays.toString(this)
        this is LongArray -> Arrays.toString(this)
        this is FloatArray -> Arrays.toString(this)
        this is DoubleArray -> Arrays.toString(this)
        this is Array<*> -> Arrays.deepToString(this)
        else -> "Couldn't find a correct type for the object"
    }
}
