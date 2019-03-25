@file:JvmName("ActivityUtil")
package com.lindroid.androidutilskt.extension

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * @author Lin
 * @date 2018/10/30
 * @function Activity工具类
 * @Description
 */

/**
 * 启动一个Activity
 */
inline fun <reified T : Activity> Context.launchActivity(intent: Intent = Intent(this, T::class.java)) {
    startActivity(intent)
}



