package com.lindroid.androidutilskt.extension.statusbar

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.support.annotation.RequiresPermission
import android.text.TextUtils
import android.util.Log
import java.util.*

/**
 * @author Lin
 * @date 2018/10/22
 * @function 手机设备工具类
 * @Description
 */
private const val STATUSBAR_KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
private const val STATUSBAR_KEY_FLYME_VERSION_NAME = "ro.build.display.id"
private const val FLYME = "flyme"

/**
 * MIUI系统版本号，返回-1表示不是MIUI系统
 */
private val miuiVersionCode: Int
    get() {
        return try {
            val clz = Class.forName("android.os.SystemProperties")
            val mtd = clz.getMethod("get", String::class.java)
            var versionName = mtd.invoke(null, "ro.miui.ui.version.name") as String
            versionName = versionName.replace("[vV]".toRegex(), "")
            versionName.toInt()
        } catch (e: Exception) {
            -1
        }
    }

/**
 * 是否是MIUI9及以上版本
 */
val isMIUI9ABOVE = miuiVersionCode >= 9
/**
 * 是否是MIUI9以下版本
 */
val isMIUI9BELOW = miuiVersionCode < 9
/**
 * 是否是MIUI6及以上版本
 */
val isMIUI6ABOVE = miuiVersionCode >= 6
/**
 * 是否是MIUI6以下版本
 */
val isMIUI6BELOW = miuiVersionCode < 6

/**
 * 是否是MIUI系统
 */
val isMIUI = miuiVersionCode > 0
/**
 * 是否是Flyme系统
 */
val isFlyme: Boolean
    get() {
        return try {
            val versionName = getOsVersionName(STATUSBAR_KEY_FLYME_VERSION_NAME)
            versionName.isNotEmpty() && versionName.contains(FLYME)
        } catch (e: Exception) {
            false
        }
    }

/**
 * 判断Flyme是否是6.2.0.0A及以上版本
 */
val isFlyme6Above: Boolean
    get() {
        return try {
            val name = getOsVersionName(STATUSBAR_KEY_FLYME_VERSION_NAME).replace(" ", "").substringAfter(FLYME)
            name.contains("6.2.0.0A") || name[0].toInt() > 6
        } catch (e: Exception) {
            Log.e("DeviceUtil", "判断flyme6系统失败")
            false
        }
    }

/**
 * 获取Mac地址
 */
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
@SuppressLint("HardwareIds")
fun Context.getMac(): String {
    val wifiManager: WifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val mac = wifiManager.connectionInfo.macAddress
    return if (TextUtils.isEmpty(mac)) "" else mac
}


/**
 * 获取手机操作系统名称
 */
fun getOsVersionName(key: String): String {
    val properties = Properties()
    val clzSystemProperties: Class<*>? = Class.forName("android.os.SystemProperties")
    val method = clzSystemProperties?.getDeclaredMethod("get", String::class.java)
    var name = properties.getProperty(key)
    if (name == null) {
        name = try {
            method?.invoke(null, key) as String
        } catch (e: Exception) {
            null
        }
    }
//    name = name?.toLowerCase() ?: ""
    name = name ?: ""
    return name
}


/**
 * 获取deviceId
 */
val deviceId: String
    get() {
        val szDevIDShort = with(StringBuilder("35")) {
            append(Build.BOARD.length % 10)
            append(Build.BRAND.length % 10)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                append(Build.SUPPORTED_ABIS[0].length % 10)
            } else {
                append(Build.CPU_ABI.length % 10)
            }
            append(Build.DEVICE.length % 10)
            append(Build.DISPLAY.length % 10)
            append(Build.HOST.length % 10)
            append(Build.ID.length % 10)
            append(Build.MANUFACTURER.length % 10)
            append(Build.MODEL.length % 10)
            append(Build.PRODUCT.length % 10)
            append(Build.TAGS.length % 10)
            append(Build.TYPE.length % 10)
            append(Build.USER.length % 10)
        }.toString()
        val serial = try {
            Build::class.java.getField("SERIAL").get(null).toString()
        } catch (e: Exception) {
            "serial" // 随便一个初始化
        }
        return UUID(szDevIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()

    }