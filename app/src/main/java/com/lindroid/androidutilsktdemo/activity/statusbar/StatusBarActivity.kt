package com.lindroid.androidutilsktdemo.activity.statusbar

import com.lindroid.androidutilskt.extension.launchActivity
import com.lindroid.androidutilskt.extension.px2dp
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroid.androidutilskt.extension.statusbar.getStatusBarHeight
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_status_bar.*


/**
 * @author Lin
 * @date 2019/3/1
 * @function 状态栏工具类
 * @Description
 */
@EnableDragToClose
class StatusBarActivity(override val contentViewId: Int = R.layout.activity_status_bar) : BaseActivity() {
    override fun initView() {
        super.initView()

        initToolBar(R.string.util_status_bar)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnHeight.setOnClickListener {
            shortToast("状态栏高度：${px2dp(getStatusBarHeight())}dp")
        }
        btnChangeColor.setOnClickListener {
            launchActivity<StatusBarChangeColorActivity>()
        }
        btnSlide.setOnClickListener {
            launchActivity<StatusBarSlideActivity>()
        }

    }
}
