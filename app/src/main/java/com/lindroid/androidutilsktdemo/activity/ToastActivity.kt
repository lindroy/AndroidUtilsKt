package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.longToast
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_toast.*

/**
 * @author Lin
 * @date 2019/2/27
 * @function Toast工具类
 * @Description
 */
@EnableDragToClose
class ToastActivity(override val contentViewId: Int = R.layout.activity_toast) : BaseActivity() {

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_toast)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnShortToast.setOnClickListener { shortToast("短Toast") }
        btnLongToast.setOnClickListener { longToast("长Toast") }
    }
}
