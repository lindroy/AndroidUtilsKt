package com.lindroid.androidutilskt.extension

import android.util.Patterns

/**
 * @author Lin
 * @date 2019/3/14
 * @function 正则表达式工具类
 * @Description
 */
private fun String.checkWithRegex(pattern: String): Boolean {
    return Regex(pattern).matches(this)
}

/**校验内地手机号码**/
fun String.isMobile(): Boolean =
    checkWithRegex("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$")

/**校验电子邮箱**/
fun String.isEmail(): Boolean = checkWithRegex(Patterns.EMAIL_ADDRESS.pattern())

/**校验网络链接**/
fun String.isWebUrl(): Boolean = checkWithRegex(Patterns.WEB_URL.pattern())

/**校验数字**/
fun String.isNumber(): Boolean = checkWithRegex("^[0-9]*$")

/**校验字母（无关大小写）**/
fun String.isLetter(): Boolean = checkWithRegex("^[A-Za-z]+$")

/**校验大写字母**/
fun String.isUpperCaseLetter(): Boolean = checkWithRegex("^[A-Z]+$")

/**校验小写字母**/
fun String.isLowerCaseLetter(): Boolean = checkWithRegex("^[a-z]+$")

/**校验汉字**/
fun String.isChinese(): Boolean = checkWithRegex("^[\\u4e00-\\u9fa5]+$")

/**校验QQ号码**/
fun String.isQQ(): Boolean = checkWithRegex("[1-9][0-9]{4,14}")

/**校验澳门手机号码**/
fun String.isMacaoMobile(): Boolean = checkWithRegex("[6]\\d{7}")