package com.lindroid.androidutilsktdemo.activity.statusbar

import com.lindroid.androidutilskt.extension.statusbar.getStatusBarHeight
import com.lindroid.androidutilskt.extension.statusbar.setTransParentStatusBar
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_status_bar_image.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @author Lin
 * @date 2019/3/4
 * @function 顶部为图片的沉浸式状态栏
 * @Description
 */

@EnableDragToClose
class StatusBarImageActivity(override val contentViewId: Int = R.layout.activity_status_bar_image) : BaseActivity() {

    override fun initView() {
        super.initView()
        initToolBar("顶部为图片的沉浸式状态栏", toolBarColor = android.R.color.transparent)
        setTransParentStatusBar()

        /* val lp = FrameLayout.LayoutParams(toolBar.layoutParams)
         lp.setMargins(0,getStatusBarHeight(),0,0)
         toolBar.layoutParams = lp*/

        toolBar.setPadding(0, getStatusBarHeight(), 0, 0)
        tvDesc.text = """
顶部为图片的沉浸式状态栏的设置步骤：
1、设置透明状态栏；
2、标题栏的高度设为“wrap_content”，固定高度可通过“minHeight”设置；
3、在代码中将标题栏的paddingTop或者marginTop设为状态栏高度。
        """.trimIndent()
    }

}
