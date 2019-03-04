package com.lindroid.androidutilsktdemo.activity.statusbar

import com.lindroid.androidutilskt.extension.statusbar.*
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_status_bar_change_color.*

/**
 * @author Lin
 * @date 2019/3/1
 * @function 改变状态栏颜色
 * @Description
 */
@EnableDragToClose
class StatusBarChangeColorActivity(override val contentViewId: Int = R.layout.activity_status_bar_change_color) :
    BaseActivity() {

    override fun initView() {
        super.initView()
//        initToolBar("改变状态栏颜色", toolBarColor = android.R.color.transparent)
//        toolBar.setPadding(0, getStatusBarHeight(), 0, 0)
        setTransParentStatusBar()
    }

    override fun initOnClick() {
        super.initOnClick()
        btnTransparent.setOnClickListener {
            setStatusBarColorRes(android.R.color.transparent)
        }
        btnWhite.setOnClickListener {
            setStatusBarColorRes(android.R.color.white)
        }
        btnBlack.setOnClickListener {
            setStatusBarColorRes(android.R.color.black)
        }
        btnGradient.setOnClickListener {
            setGradientStatusBar(R.drawable.shape_gradient_bar)
        }
        btnLight.setOnClickListener {
            setStatusBarLightMode()
        }
        btnDark.setOnClickListener {
            setStatusBarDarkMode()
        }

    }
}
