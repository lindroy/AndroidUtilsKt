package com.lindroid.androidutilskt.extension.logcat

import java.util.*

/**
 * @author Lin
 * @date 2019/3/20
 * @function
 * @Description
 */
internal fun Any?.transToString(): String {
    return when (this) {
        null -> "null"
        !this.javaClass.isArray -> this.toString()
        is BooleanArray -> Arrays.toString(this)
        is CharArray -> Arrays.toString(this)
        is ByteArray -> Arrays.toString(this)
        is ShortArray -> Arrays.toString(this)
        is IntArray -> Arrays.toString(this)
        is LongArray -> Arrays.toString(this)
        is FloatArray -> Arrays.toString(this)
        is DoubleArray -> Arrays.toString(this)
        is Array<*> -> Arrays.deepToString(this)
        else -> "Couldn't find a correct type for the object"
    }
}