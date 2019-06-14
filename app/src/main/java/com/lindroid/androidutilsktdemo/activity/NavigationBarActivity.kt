package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilskt.extension.logcat.d
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
        tvHasNav.text = "是否有虚拟导航栏：$hasNavBar"
        tvNavHeight.text = "虚拟导航栏高度：${navBarHeight}px"
    }

    override fun initOnListener() {
        super.initOnListener()
        setOnNavBarStatusWatcher {
            "导航栏是否显示：$it".d()
        }
//        setStatusBarColorRes(R.color.colorPrimary)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        tvNavShow.text = "虚拟导航栏是否显示：$isNavBarShowed"
        setNavBarColorRes(android.R.color.holo_red_light)
        swNav.setOnCheckedChangeListener { buttonView, isChecked ->
            window.setShowNavBar(isChecked)
           /* if (isChecked){
                llRoot.setNewPadding(top = 0)
            }else{
                llRoot.setNewPadding(top = statusBarHeight)

            }*/
        }

    }
}
