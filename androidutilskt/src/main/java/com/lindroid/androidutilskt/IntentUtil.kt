package com.lindroid.androidutilskt

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings

/**
 * @author Lin
 * @date 2019/2/28
 * @function
 * @Description
 */
object IntentUtil {
    private var browserFailListener: (() -> Unit)? = null
    /**
     * 打开系统设置界面
     */
    @JvmStatic
    fun launchSystemSetting(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    /**
     * 打开wifi设置界面
     */
    @JvmStatic
    fun launchWifiSetting(context: Context) {
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    /**
     * 打开拨号面板
     */
    @JvmStatic
    fun launchDialPage(context: Context) {
        context.startActivity(Intent(Intent.ACTION_DIAL))
    }

    /**
     * 调用浏览器并打开一个网页
     * @param url : 网页地址
     * @param browserListener : 是否成功打开浏览器监听
     */
    @JvmStatic
    fun launchBrowse(context: Context, url: String, browserListener: ((isSuccess: Boolean) -> Unit)? = null) {
        val intentWeb = with(Intent(Intent.ACTION_VIEW)) {
            addCategory(Intent.CATEGORY_BROWSABLE)
            data = Uri.parse(url)
            this
        }
        if (intentWeb.resolveActivity(context.packageManager) != null) {
            browserListener?.invoke(true)
            context.startActivity(Intent.createChooser(intentWeb, "请选择浏览器"))
        } else {
            browserListener?.invoke(false)
        }
    }

    /**
     * 直接拨打电话
     */
    @JvmStatic
    @SuppressLint("MissingPermission")
    fun callPhone(context: Context, phoneNumber: String) {
        context.startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber")))
    }

    /**
     * 启动系统相机
     */
    @JvmStatic
    fun launchCamera(activity: Activity, requestCode: Int) {
        activity.startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), requestCode)
    }

    /**
     * 发送短信
     * @param phoneNumber 手机号码
     * @param message 短信内容
     */
    @JvmStatic
    fun sendSMS(context: Context, phoneNumber: String = "", message: String = "") {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber"))
        intent.putExtra("sms_body", message)
        context.startActivity(intent)
    }

}

