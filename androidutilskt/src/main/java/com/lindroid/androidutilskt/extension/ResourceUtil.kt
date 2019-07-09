@file:JvmName("ResourceUtil")
package com.lindroid.androidutilskt.extension

import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2018/11/9
 * @function 获取图片、颜色等资源
 * @Description
 */

/**
 * 获取颜色
 */
fun getResColor(@ColorRes colorRes: Int) = ContextCompat.getColor(AndUtil.appContext, colorRes)

/**
 * 获取图片资源
 */
fun getResDrawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(AndUtil.appContext, drawableRes)

/**
 * 获取字符资源
 */
fun getResString(@StringRes stringId:Int,vararg formatArgs:Any) =AndUtil.appContext.getString(stringId,*formatArgs)

/**
 * 获取dimens资源
 * 单位为px
 */
fun getResDimenPx(@DimenRes dimenRes: Int) = AndUtil.appContext.resources.getDimensionPixelSize(dimenRes)

/**
 * 获取dimens中单位为dp的资源
 */
fun getResDimenDp(@DimenRes dimenRes: Int) = px2dp(AndUtil.appContext.resources.getDimensionPixelSize(dimenRes))

/**
 * 获取dimens中单位为Sp的资源
 */
fun getResDeminSp(@DimenRes dimenRes: Int) = px2sp(AndUtil.appContext.resources.getDimensionPixelSize(dimenRes))