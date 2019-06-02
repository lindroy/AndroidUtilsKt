package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilskt.extension.logcat.d
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
        btnToggle.setOnClickListener { editText.toggleKeyboard() }
        llRoot.addOnKeyboardStatusWatcher { hasShow, keyboardHeight ->
            "软键盘状态：${hasShow}，键盘高度：$keyboardHeight".d()
            "软键盘是否打开：${llRoot.isKeyboardShowed}".d()
        }

        btnHide.addOnKeyboardStatusWatcher { hasShow, keyboardHeight ->
            "软键盘状态2：${hasShow}，键盘高度2：$keyboardHeight".d()
        }




    }
}
