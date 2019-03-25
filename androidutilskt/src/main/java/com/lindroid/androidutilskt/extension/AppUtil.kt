@file:JvmName("AppUtil")
package com.lindroid.androidutilskt.extension

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import java.io.File

/**
 * @author Lin
 * @date 2018/10/26
 * @function App信息工具类
 * @Description
 */

private const val UNKOWN = "unKnown"

/**
 * 获取应用版本名称，默认为本应用
 * @return 失败时返回unKnown
 */
fun Context.getAppVersionName(packageName: String = this.packageName): String {
    return try {
        if (packageName.isBlank()) {
            return UNKOWN
        } else {
            val pi = packageManager.getPackageInfo(packageName, 0)
            pi?.versionName ?: UNKOWN
        }
    } catch (e: PackageManager.NameNotFoundException) {
        UNKOWN
    }
}

/**
 * 获取应用版本号，默认为本应用
 * @return 失败时返回-1
 */
fun Context.getAppVersionCode(packageName: String = this.packageName): Int {
    return try {
        if (packageName.isBlank()) {
            -1
        } else {
            val pi = packageManager.getPackageInfo(packageName, 0)
            pi?.versionCode ?: -1
        }
    } catch (e: PackageManager.NameNotFoundException) {
        -1
    }
}

/**
 * 获取应用大小，单位为b，默认为本应用
 * @return 失败时返回-1
 */
fun Context.getAppSize(packageName: String = this.packageName): Long {
    return try {
        if (packageName.isBlank()) {
            -1
        } else {
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            File(applicationInfo.sourceDir).length()
        }
    } catch (e: PackageManager.NameNotFoundException) {
        -1
    }
}

/**
 * 获取应用图标，默认为本应用
 * @return 失败时返回null
 */
fun Context.getAppIcon(packageName: String = this.packageName): Drawable? {
    return try {
        if (packageName.isBlank()) {
            null
        } else {
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            applicationInfo.loadIcon(packageManager)
        }
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
}

