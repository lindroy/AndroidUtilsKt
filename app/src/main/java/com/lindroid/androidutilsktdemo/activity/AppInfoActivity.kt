package com.lindroid.androidutilsktdemo.activity

import android.view.View
import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilskt.extension.logcat.d
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_app_info.*

/**
 * @author Lin
 * @date 2018/10/26
 * @function 应用信息
 * @Description
 */
@EnableDragToClose
class AppInfoActivity(override val contentViewId: Int = R.layout.activity_app_info) : BaseActivity() {


    override fun initView() {
        super.initView()
        initToolBar(R.string.util_app_info)
        tvVersionName.text = "版本名：${getAppVersionName()}"
        tvVersionCode.text = "版本号：${getAppVersionCode()}"
        tvAppSize.text = "应用大小：${getAppSize()}"
        ivIcon.setImageDrawable(getAppIcon())
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 shl 30) - 1, View.MeasureSpec.AT_MOST)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 shl 30) - 1, View.MeasureSpec.AT_MOST)
        ivIcon.measure(widthMeasureSpec, heightMeasureSpec)
        "ivIcon.viewHeight=${px2dp(ivIcon.measuredHeight)},ivIcon.viewWidth=${px2dp(ivIcon.measuredWidth)}".d()
    }
}
