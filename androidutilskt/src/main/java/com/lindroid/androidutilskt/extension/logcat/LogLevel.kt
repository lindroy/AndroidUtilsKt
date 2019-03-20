package com.lindroid.androidutilskt.extension.logcat

import android.support.annotation.IntDef

/**
 * @author Lin
 * @date 2019/3/19
 * @function 日志等级
 * @Description
 */

const val VERBOSE = 1
const val DEBUG = 2
const val INFO = 3
const val WARN = 4
const val ERROR = 5
const val ASSERT = 6

@IntDef(VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.SOURCE)
annotation class LogLevel