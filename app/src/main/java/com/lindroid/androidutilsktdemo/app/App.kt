package com.lindroid.androidutilsktdemo.app

import android.app.Application
import com.lindroid.androidutilskt.app.AndUtil
import com.squareup.leakcanary.LeakCanary
import com.youngfeng.snake.Snake


/**
 * @author Lin
 * @date 2019/2/27
 * @function
 * @Description
 */
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        // 对Snake进行初始化
        Snake.init(this)
        AndUtil.init(this)
            .setLogGlobalConfig {
                setLogEnable(true)
                setMethodCount(2)
            }
            .setLogDiskConfig {  }

//            .setDefaultSpFile()     //设置SharePreferences的默认表名，默认为“sp_util”
//            .setServerTimeFormat()  //设置时间格式化中服务器时间格式，默认为“yyyy-MM-dd'T'HH:mm:ss.SSS”
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}