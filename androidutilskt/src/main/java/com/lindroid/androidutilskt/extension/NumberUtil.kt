package com.lindroid.androidutilskt.extension

import java.text.NumberFormat

/**
 * @author Lin
 * @date 2019/6/1
 * @function
 * @Description
 */
/**
 * 去除小数点后面的0
 * @see removeLastZero
 */
fun String?.removeLastZero(isGroupingUsed: Boolean = false) = when (isNullOrEmpty()) {
    true -> ""
    false ->this!!.toDouble().removeLastZero(isGroupingUsed)
}

/**
 * 去除小数点后面的0
 * @param isGroupingUsed:是否使用千分分隔符，默认为false不使用
 */
fun Double?.removeLastZero(isGroupingUsed: Boolean = false) = when (this == null) {
    true -> ""
    false -> {
        NumberFormat.getInstance().let {
            it.isGroupingUsed = isGroupingUsed
            it.format(this) ?: ""
        }
    }
}