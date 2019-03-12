package com.lindroid.androidutilskt.extension

import android.graphics.Color
import android.graphics.Typeface
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
    content: String,
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
    text = with(SpannableString(content)) {
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
                listener.invoke(content.substring(start, end), widget)
            }
        }, start, end, flag)
        this
    }
}

/**
 * @param style: @link Typeface
 *
 */
fun TextView.setStyleSpan(
    content: String,
    start: Int,
    end: Int,
    style: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = this.apply {
    text = with(SpannableString(content)) {
        setSpan(StyleSpan(style), start, end, flag)
        this
    }
}

/**
 * 设置粗体字
 */
fun TextView.setBoldSpan(
    content: String,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = setStyleSpan(content, start, end, Typeface.BOLD, flag)

/**
 * 设置斜体字
 */
fun TextView.setItalicSpan(
    content: String,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = setStyleSpan(content, start, end, Typeface.ITALIC, flag)


/**
 * 设置粗体和斜体字
 */
fun TextView.setBoldItalicSpan(
    content: String,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = setStyleSpan(content, start, end, Typeface.BOLD_ITALIC, flag)

/**
 * 设置超链接点击事件
 * @param url:超链接地址
 */
fun TextView.setUrlSpan(
    content: String,
    url: String,
    start: Int,
    end: Int,
    isUnderlineText: Boolean = false,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE,
    @ColorInt textColor: Int = -1,
    @ColorInt bgColor: Int = Color.TRANSPARENT,
    @ColorInt highlightColor: Int = Color.TRANSPARENT
) = this.apply {
    movementMethod = LinkMovementMethod.getInstance()
    text = with(SpannableString(content)) {
        setSpan(object : URLSpan(url) {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                if (textColor != -1) {
                    ds.color = textColor
                }
                ds.bgColor = bgColor
                ds.isUnderlineText = isUnderlineText
                this@setUrlSpan.highlightColor = highlightColor
            }
        }, start, end, flag)
        this
    }
}

/**
 * 设置文字相对大小
 * @param proportion:放缩比例
 */
fun TextView.setRelativeSize(
    content: String,
    start: Int,
    end: Int,
    proportion: Float,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = this.apply {
    text = with(SpannableString(content)) {
        setSpan(RelativeSizeSpan(proportion), start, end, flag)
        this
    }
}

/**
 * 设置文字上标
 */
fun TextView.setSuperScriptSpan(
    content: String,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = this.apply {
    text = with(SpannableString(content)) {
        setSpan(SuperscriptSpan(), start, end, flag)
        this
    }
}

/**
 * 设置文字下标
 */
fun TextView.setSubscriptSpan(
    content: String,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = this.apply {
    text = with(SpannableString(content)) {
        setSpan(SubscriptSpan(), start, end, flag)
        this
    }
}



