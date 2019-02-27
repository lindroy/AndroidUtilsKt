package com.lindroid.androidutilskt.app

import android.app.Application
import android.content.Context

/**
 * @author Lin
 * @date 2019/2/27
 * @function 初始化类
 * @Description
 */
object AndUtil {
    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    @JvmStatic
    val appContext: Context
        get() = application.applicationContext
}