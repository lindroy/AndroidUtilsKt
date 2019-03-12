package com.lindroid.androidutilskt.extension

import android.graphics.Color
import android.support.annotation.ColorInt
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView

/**
 * @author Lin
 * @date 2019/3/12
 * @function 文字效果工具类
 * @Description
 */

/**
 * 设置前景色
 * @param color: 前景色值
 */
@JvmOverloads
fun TextView.setFgColorSpan(
    string: String,
    @ColorInt color: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = with(SpannableString(string)) {
    setSpan(ForegroundColorSpan(color), start, end, flag)
    this@setFgColorSpan.text = this
    this@setFgColorSpan
}

/**
 * 设置背景色
 * @param color: 背景色值
 */
@JvmOverloads
fun TextView.setBgColorSpan(
    string: String,
    @ColorInt color: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = with(SpannableString(string)) {
    setSpan(BackgroundColorSpan(color), start, end, flag)
    this@setBgColorSpan.text = this
    this@setBgColorSpan
}

/**
 * 设置删除线
 */
@JvmOverloads
fun TextView.setStrikethroughSpan(
    string: String,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = with(SpannableString(string)) {
    setSpan(StrikethroughSpan(), start, end, flag)
    this@setStrikethroughSpan.text = this
    this@setStrikethroughSpan
}

/**
 * 设置下划线
 */
@JvmOverloads
fun TextView.setUnderlineSpan(
    string: String,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = with(SpannableString(string)) {
    setSpan(UnderlineSpan(), start, end, flag)
    this@setUnderlineSpan.text = this
    this@setUnderlineSpan
}


/**
 * 设置可点击文本
 * @param textColor: 文字颜色
 * @param bgColor: 背景颜色
 * @param highlightColor: 点击后的文字背景色
 */
@JvmOverloads
fun TextView.setClickableSpan(
    string: String,
    start: Int,
    end: Int,
    isUnderlineText: Boolean = false,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE,
    @ColorInt textColor: Int = -1,
    @ColorInt bgColor: Int = Color.TRANSPARENT,
    @ColorInt highlightColor: Int = Color.TRANSPARENT,
    listener: (clickString: String, widget: View) -> Unit

) = this.apply {
    movementMethod = LinkMovementMethod.getInstance()
    text = with(SpannableString(string)) {
        setSpan(object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                if (textColor != -1) {
                    ds.color = textColor
                }
                ds.bgColor = bgColor
                ds.isUnderlineText = isUnderlineText
                this@setClickableSpan.highlightColor = highlightColor
            }

            override fun onClick(widget: View) {
                listener.invoke(string.substring(start, end), widget)
            }
        }, start, end, flag)
        this
    }
}













