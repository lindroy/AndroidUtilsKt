@file:JvmName("NetworkUtil")

package com.lindroid.androidutilskt.extension

import android.content.Context
import android.net.ConnectivityManager
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2018/10/19
 * @function 网络相关工具类
 * @Description
 */

/**没有网络**/
const val NETWORK_NONE = -1
/**网络连接**/
//const val NETWORK_CONNECTED = 0
/**移动网络**/
const val NETWORK_MOBILE = 1
/**无线网络**/
const val NETWORK_WIFI = 2
/**未知网络**/
const val NETWORK_UNKNOWN = -2

/**
 * 获取当前的网络状态
 */
val networkState: Int
    get() {
        //得到连接管理器对象
        val cm = AndUtil.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo

        //如果网络连接，判断该网络类型
        return when (networkInfo != null && networkInfo.isAvailable) {
            true -> when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> NETWORK_WIFI
                ConnectivityManager.TYPE_MOBILE -> NETWORK_MOBILE
                else -> {
                    NETWORK_UNKNOWN
                }
            }
            false -> NETWORK_NONE
        }
    }

/**是否是wifi**/
val isWifi
    get() = networkState == NETWORK_WIFI

/**是否是移动网络**/
val isMobileNet
    get() = networkState == NETWORK_MOBILE

/**网络是否连接**/
val isNetworkConnect
    get() = when (networkState) {
        NETWORK_MOBILE, NETWORK_WIFI -> true
        NETWORK_NONE -> false
        else -> {
            false
        }
    }
