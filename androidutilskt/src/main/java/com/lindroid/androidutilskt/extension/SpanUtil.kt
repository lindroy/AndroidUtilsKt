package com.lindroid.androidutilskt.extension

import android.graphics.Color
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.text.SpannableString
import android.text.SpannableStringBuilder
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
    content: CharSequence,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
) = with(SpannableString(content)) {
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
 * @param isUnderlineText: 是否显示下划线
 * @param textColor: 文字颜色
 * @param bgColor: 背景颜色
 * @param highlightColor: 点击后的文字背景色
 * @param listener: 点击回调，参数为点击的文本内容和View
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
 * 设置文字样式
 * @param style:文字样式
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
 * 设置超链接
 * @param url:超链接地址
 * @param isUnderlineText: 是否显示下划线
 * @param textColor: 文字颜色
 * @param bgColor: 背景颜色
 * @param highlightColor: 点击后的文字背景色
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
fun TextView.setRelativeSizeSpan(
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


/**
 * 创建一个SpannableStringBuilder实例，用于链式调用API
 */
@JvmOverloads
fun TextView.buildSpan(text: CharSequence, init: (SpanHelper.Builder.() -> Unit)? = null) =
    SpanHelper.build(this, text, init)

class SpanHelper {

    companion object {
        fun build(textView: TextView, text: CharSequence, init: (Builder.() -> Unit)? = null) =
            Builder(textView, text, init)
    }

    /**
     * SpannableString构造类
     */
    class Builder private constructor() {

        private lateinit var spBuilder: SpannableStringBuilder

        /**
         * 需要设置文字特效的TextView
         */
        private lateinit var textView: TextView

        /**
         * 文字特效开始的位置下标
         */
        private var startIndex = 0

        /**
         * 文字特效结束的位置下标
         */
        private var endIndex = 0

        /**
         * 设置标识，一共四种
         * Spanned#SPAN_INCLUSIVE_EXCLUSIVE
         * Spanned#SPAN_INCLUSIVE_INCLUSIVE
         * Spanned#SPAN_EXCLUSIVE_EXCLUSIVE
         * Spanned#SPAN_EXCLUSIVE_INCLUSIVE
         */
        private var flag = Spanned.SPAN_INCLUSIVE_INCLUSIVE

        constructor(textView: TextView, text: CharSequence, init: (Builder.() -> Unit)?) : this() {
            this.textView = textView
            spBuilder = SpannableStringBuilder(text)
            init?.let { it() }
        }

        /**
         * 设置文字特效开始的位置下标
         */
        fun setStart(start: Int) = apply { startIndex = start }

        /**
         * 设置文字特效结束的位置下标
         */
        fun setEnd(end: Int) = apply { endIndex = end }

        fun setStartEnd(start: Int, end: Int) = apply {
            setStart(start)
            setEnd(end)
        }

        /**
         * 设置标识
         */
        fun setFlag(flag: Int) = apply { this.flag = flag }

        /**
         * 设置前景色
         * @param color: 前景色值
         */
        @JvmOverloads
        fun setFgColor(@ColorInt color: Int, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(ForegroundColorSpan(color), start, end, flag) }

        /**
         * 设置背景色
         * @param color: 背景色值
         */
        @JvmOverloads
        fun setBgColor(@ColorInt color: Int, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(BackgroundColorSpan(color), start, end, flag) }

        /**
         * 设置删除线
         */
        @JvmOverloads
        fun setStrikethrough(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(StrikethroughSpan(), start, end, flag) }

        /**
         * 设置下划线
         */
        @JvmOverloads
        fun setUnderline(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(UnderlineSpan(), start, end, flag) }

        /**
         * 设置可点击文本
         * @param isUnderlineText: 是否显示下划线
         * @param textColor: 文字颜色
         * @param bgColor: 背景颜色
         * @param highlightColor: 点击后的文字背景色
         * @param listener: 点击回调，参数为点击的文本内容和View
         */
        @JvmOverloads
        fun setClickable(
            start: Int = startIndex,
            end: Int = endIndex,
            isUnderlineText: Boolean = false,
            flag: Int = this.flag,
            @ColorInt textColor: Int = -1,
            @ColorInt bgColor: Int = Color.TRANSPARENT,
            @ColorInt highlightColor: Int = Color.TRANSPARENT,
            listener: (clickString: String, widget: View) -> Unit
        ) = apply {
            textView.movementMethod = LinkMovementMethod.getInstance()
            spBuilder.setSpan(object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    if (textColor != -1) {
                        ds.color = textColor
                    }
                    ds.bgColor = bgColor
                    ds.isUnderlineText = isUnderlineText
                    textView.highlightColor = highlightColor
                }

                override fun onClick(widget: View) {

                    listener.invoke(spBuilder.substring(start, end), widget)
                }

            }, start, end, flag)
        }

        /**
         * 设置超链接
         * @param url:超链接地址
         * @param isUnderlineText: 是否显示下划线
         * @param textColor: 文字颜色
         * @param bgColor: 背景颜色
         * @param highlightColor: 点击后的文字背景色
         */
        fun setUrl(
            url: String,
            start: Int = startIndex,
            end: Int = endIndex,
            isUnderlineText: Boolean = false,
            @ColorInt textColor: Int = -1,
            @ColorInt bgColor: Int = Color.TRANSPARENT,
            @ColorInt highlightColor: Int = Color.TRANSPARENT,
            flag: Int = this.flag
        ) = apply {
            textView.movementMethod = LinkMovementMethod.getInstance()
            spBuilder.setSpan(object : URLSpan(url) {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    if (textColor != -1) {
                        ds.color = textColor
                    }
                    ds.bgColor = bgColor
                    ds.isUnderlineText = isUnderlineText
                    textView.highlightColor = highlightColor
                }
            }, start, end, flag)
        }

        /**
         * 设置文字样式
         * @param style:文字样式
         */
        @JvmOverloads
        fun setStyle(style: Int, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(StyleSpan(style), start, end, flag) }

        /**
         * 设置文字为粗体
         */
        @JvmOverloads
        fun setBold(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { setStyle(Typeface.BOLD, start, end, flag) }

        /**
         * 设置文字为斜体
         */
        @JvmOverloads
        fun setItalic(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { setStyle(Typeface.ITALIC, start, end, flag) }

        /**
         * 设置文字为粗体和斜体
         */
        @JvmOverloads
        fun setBoldItalic(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { setStyle(Typeface.BOLD_ITALIC, start, end, flag) }


        /**
         * 设置文字相对大小
         * @param proportion:放缩比例
         */
        fun setRelativeSize(proportion: Float, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(RelativeSizeSpan(proportion), start, end, flag) }

        /**
         * 设置文字上标
         */
        fun setSuperScript(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(SuperscriptSpan(), start, end, flag) }

        /**
         * 设置文字下标
         */
        fun setSubscript(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
            apply { spBuilder.setSpan(SubscriptSpan(), start, end, flag) }

        fun create() {
            textView.text = spBuilder
        }
    }
}


