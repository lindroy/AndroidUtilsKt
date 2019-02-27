package com.lindroid.androidutilskt.extension

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat

/**
 * @author Lin
 * @date 2018/11/9
 * @function 获取图片、颜色等资源
 * @Description
 */

/**
 * 获取颜色
 */
fun Context.getResColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

/**
 * 获取图片资源
 */
fun Context.getResDrawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)