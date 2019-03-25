@file:JvmName("SDCardUtil")
package com.lindroid.androidutilskt.extension

import android.os.Environment
import android.os.StatFs

/**
 * @author Lin
 * @date 2019/3/19
 * @function SD卡工具类
 * @Description
 */

/**
 * SD卡是否已挂载
 */
val isSDCardMounted
    get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/**
 * 获取SD卡路径
 */
fun getSDCardPath(): String = if (isSDCardMounted) Environment.getExternalStorageDirectory().absolutePath else ""

/**
 * 获取SD卡的总大小
 */
fun getSDCardTotalSize(): Long = when (isSDCardMounted) {
    true -> {
        val statFs = StatFs(Environment.getExternalStorageDirectory().path)
        statFs.blockSizeLong * statFs.blockCountLong
    }
    false -> -1
}


/**
 * 获取SD卡可用空间大小
 */
fun getSDCardAvailableSize(): Long = when (isSDCardMounted) {
    true -> {
        val statFs = StatFs(Environment.getExternalStorageDirectory().path)
        statFs.blockSizeLong * statFs.availableBlocksLong
    }
    false -> -1
}