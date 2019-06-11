package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.hasNavigationBar
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_navigation_bar.*

/**
 * @author Lin
 * @date 2019/6/11
 * @function 虚拟状态栏
 * @Description
 */
@EnableDragToClose
class NavigationBarActivity(override val contentViewId: Int = R.layout.activity_navigation_bar) : BaseActivity() {

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_navigation)
        tvHasNav.text = "是否有虚拟导航栏：$hasNavigationBar"
    }
}
