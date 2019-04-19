package com.lindroid.androidutilskt.app

import android.app.Application
import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.lindroid.androidutilskt.extension.logcat.LogConfig
import com.lindroid.androidutilskt.extension.serverFormat
import com.lindroid.androidutilskt.extension.setSpDefaultFile

/**
 * @author Lin
 * @date 2019/2/27
 * @function 初始化类
 * @Description
 */
object AndUtil {
    private lateinit var application: Application

    fun init(application: Application): Config {
        this.application = application
        return Config.build()
    }

    @JvmStatic
    internal val appContext: Context
        get() = application.applicationContext

    @JvmStatic
    internal fun getString(@StringRes strId: Int): String = appContext.getString(strId)

    @JvmStatic
    internal fun getColor(@ColorRes colorId: Int): Int = ContextCompat.getColor(appContext, colorId)

    class Config {
        companion object {
            fun build() = Config()
        }

        /**
         * 设置默认的SharePreference表名
         */
        fun setDefaultSpFile(fileName: String) = this.apply { setSpDefaultFile(fileName) }

        /**
         * 设置全局的服务器时间格式
         */
        fun setServerTimeFormat(timeFormat: String) = this.apply { serverFormat = timeFormat }

        /**
         * 设置LogUtil配置
         */
        fun setLogGlobalConfig(init: LogConfig.() -> Unit) = this.apply {
            LogConfig(false, init)
        }
    }


}


