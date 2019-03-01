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
        AndUtil.init(this).setDefaultSpFile("nono")
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

    }
}