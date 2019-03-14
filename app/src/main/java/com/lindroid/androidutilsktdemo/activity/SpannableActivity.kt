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

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_Spannable)
        tvFgColor.setFgColorSpan("设置文本前景色", Color.BLUE, 4, 7)
        tvBgColor.setBgColorSpan("设置文本背景色", Color.YELLOW, 4, 7)
        tvStrike.setStrikethroughSpan("设置文本删除线", 4, 7)
        tvUnderline.setUnderlineSpan("设置文本下划线", 4, 7)
        tvBold.setBoldSpan("设置粗体字", 2, 5)
        tvItalic.setItalicSpan("设置斜体字", 2, 5)
        tvBoldItalic.setBoldItalicSpan("同时设置粗体斜体", 4, 8)
        tvClickable.setClickableSpan("设置可点击文本", 2, 5, textColor = Color.RED) { clickString, widget ->
            shortToast(clickString)
        }
        tvUrl.setUrlSpan("设置超链接", "https://www.baidu.com/", 2, 5, true, textColor = Color.BLUE)
        tvRelativeSize.setRelativeSizeSpan("设置文字相对大小", 6, 7, 1.2F)
        tvSuperScript.buildSpan("设置文字上标：210=1024") {
            setStartEnd(8, 10)
            setRelativeSize(0.6F)
            setSuperScript()
        }.create()
        tvSubscript.buildSpan("设置文字下标：H20") {
            setStartEnd(8, 9)
            setRelativeSize(0.6F)
            setSubscript()
            create()
        }

    }


}
