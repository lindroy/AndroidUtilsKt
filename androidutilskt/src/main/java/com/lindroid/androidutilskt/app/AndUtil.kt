package com.lindroid.androidutilskt.app

import android.app.Application
import android.content.Context
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
    val appContext: Context
        get() = application.applicationContext

    class Config {
        companion object {
            fun build() = Config()
        }

        /**
         * 设置默认的SharePreference表名
         */
        fun setDefaultSpFile(fileName: String) = this.apply { setSpDefaultFile(fileName) }
    }


}


