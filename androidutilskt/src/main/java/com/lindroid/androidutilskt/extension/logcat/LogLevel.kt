package com.lindroid.androidutilskt.extension.logcat

import android.support.annotation.IntDef
import android.util.Log

/**
 * @author Lin
 * @date 2019/3/19
 * @function 日志等级
 * @Description
 */

const val VERBOSE = Log.VERBOSE
const val DEBUG = Log.DEBUG
const val INFO = Log.INFO
const val WARN = Log.WARN
const val ERROR = Log.ERROR
const val ASSERT = Log.ASSERT

@IntDef(VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.SOURCE)
annotation class LogLevel