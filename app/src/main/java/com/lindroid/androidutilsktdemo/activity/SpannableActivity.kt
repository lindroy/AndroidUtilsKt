package com.lindroid.androidutilsktdemo.activity

import android.graphics.Color
import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_spannable.*

/**
 * @author Lin
 * @date 2019/3/11
 * @function Spannable工具类
 * @Description
 */
@EnableDragToClose
class SpannableActivity(override val contentViewId: Int = R.layout.activity_spannable) : BaseActivity() {
    private val content = "零一二三四五六七八九"

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_Spannable)
        tvSpannable.text = content
    }


    override fun initOnClick() {
        super.initOnClick()
        btnReset.setOnClickListener { tvSpannable.text = content }
        btnFgColor.setOnClickListener { setFgColorSpan() }
        btnBgColor.setOnClickListener { setBgColorSpan() }
        btnStrike.setOnClickListener { setStrikethroughSpan() }
        btnUnderline.setOnClickListener { setUnderlineSpan() }
        btnClickable.setOnClickListener { setClickableSpan() }
    }


    private fun setFgColorSpan() =
        tvSpannable.setFgColorSpan(content, getResColor(R.color.colorAccent), 0, 3)

    private fun setBgColorSpan() =
        tvSpannable.setBgColorSpan(content, getResColor(android.R.color.holo_orange_light), 3, 5)

    private fun setStrikethroughSpan() = tvSpannable.setStrikethroughSpan(content, 0, content.length)

    private fun setUnderlineSpan() = tvSpannable.setUnderlineSpan(content, 0, content.length)

    private fun setClickableSpan() = tvSpannable.setClickableSpan(
        content,
        5,
        9,
        textColor = Color.RED,
        bgColor = Color.YELLOW
    ) { clickString, widget ->
        shortToast(clickString)
    }

}
