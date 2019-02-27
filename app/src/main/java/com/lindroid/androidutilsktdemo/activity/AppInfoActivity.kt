package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.getAppIcon
import com.lindroid.androidutilskt.extension.getAppSize
import com.lindroid.androidutilskt.extension.getAppVersionCode
import com.lindroid.androidutilskt.extension.getAppVersionName
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
    }
}
