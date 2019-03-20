package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.logcat.AndroidLogAdapter
import com.lindroid.androidutilskt.extension.logcat.addLogAdapter
import com.lindroid.androidutilskt.extension.logcat.d
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose

/**
 * @author Lin
 * @date 2019/3/20
 * @function 日志工具类
 * @Description
 */
@EnableDragToClose
class LogActivity(override val contentViewId: Int = R.layout.activity_log) : BaseActivity() {

    override fun initBefore() {
        super.initBefore()
//        val strategy = AndroidFormatStrategy.newBuilder().build()
        addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(level: Int, tag: String?): Boolean {
                return true
            }
        })

    }

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_log)
        "默认tag的日志".d()
//        "带tag的日志".dt("MyLog")
    }
}
