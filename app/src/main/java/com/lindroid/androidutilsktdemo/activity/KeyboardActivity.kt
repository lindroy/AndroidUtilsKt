package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.hideKeyboard
import com.lindroid.androidutilskt.extension.showKeyboard
import com.lindroid.androidutilskt.extension.toggleSoftInput
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_keyboard.*

/**
 * @author Lin
 * @date 2019/2/28
 * @function
 * @Description
 */
@EnableDragToClose
class KeyboardActivity(override val contentViewId: Int = R.layout.activity_keyboard) : BaseActivity() {

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_keyboard)
    }

    override fun initOnClick() {
        super.initOnClick()
        btnShow.setOnClickListener { editText.showKeyboard() }
        btnHide.setOnClickListener { editText.hideKeyboard() }
        btnToggle.setOnClickListener { editText.toggleSoftInput() }

    }
}
