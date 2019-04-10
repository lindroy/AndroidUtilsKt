@file:JvmName("ClipboardUtil")
package com.lindroid.androidutilskt.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2018/11/21
 * @function 复制工具类
 * @Description
 */

/**
 * 复制纯文本
 * @param text: 需要复制的文本
 * @param label: 用户可见的对复制数据的描述
 * @return 是否复制成功
 */
@JvmOverloads
fun clipPlainText(text: CharSequence, label: CharSequence = ""): Boolean {
    val cm = AndUtil.appContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(label, text)
    cm.primaryClip = clipData
    return cm.hasPrimaryClip()
}
