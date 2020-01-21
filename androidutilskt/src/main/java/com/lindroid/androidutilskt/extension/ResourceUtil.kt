@file:JvmName("ResUtil")

package com.lindroid.androidutilskt.extension

import android.support.annotation.*
import android.support.v4.content.ContextCompat
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2018/11/9
 * @function 资源相关工具类
 * @Description 获取字符、图片、颜色等资源
 */

/**
 * 获取颜色
 */
@JvmName("getColor")
fun getResColor(@ColorRes colorRes: Int) = ContextCompat.getColor(AndUtil.appContext, colorRes)

/**
 * 获取图片资源
 */
@JvmName("getDrawable")
fun getResDrawable(@DrawableRes drawableRes: Int) =
    ContextCompat.getDrawable(AndUtil.appContext, drawableRes)

/**
 * 获取字符资源
 */
@JvmName("getString")
fun getResString(@StringRes stringId: Int, vararg formatArgs: Any) =
    AndUtil.appContext.getString(stringId, *formatArgs)

/**
 * 获取String数组
 */
@JvmName("getStringArray")
fun getResStringArray(@ArrayRes arrayId: Int): Array<String> =
    AndUtil.appContext.resources.getStringArray(arrayId)

/**
 * 获取Int数组
 */
@JvmName("getIntArray")
fun getResIntArray(@ArrayRes arrayId: Int) = AndUtil.appContext.resources.getIntArray(arrayId)

/**
 * 获取Char数组
 */
@JvmName("getTextArray")
fun getResTextArray(@ArrayRes arrayId: Int): Array<CharSequence> = AndUtil.appContext.resources.getTextArray(arrayId)

/**
 * 获取dimens资源
 * 单位为px
 */
@JvmName("getDimenPx")
fun getResDimenPx(@DimenRes dimenRes: Int) =
    AndUtil.appContext.resources.getDimensionPixelSize(dimenRes)

/**
 * 获取dimens中单位为dp的资源
 */
@JvmName("getDimenDp")
fun getResDimenDp(@DimenRes dimenRes: Int) =
    px2dp(AndUtil.appContext.resources.getDimensionPixelSize(dimenRes))

/**
 * 获取dimens中单位为Sp的资源
 */
@JvmName("getDimenSp")
fun getResDimenSp(@DimenRes dimenRes: Int) =
    px2sp(AndUtil.appContext.resources.getDimensionPixelSize(dimenRes))