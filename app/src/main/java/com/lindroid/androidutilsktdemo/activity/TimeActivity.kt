package com.lindroid.androidutilsktdemo.activity

import android.annotation.SuppressLint
import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_time.*

/**
 * @author Lin
 * @date 2019/3/1
 * @function 时间工具类
 * @Description
 */

@EnableDragToClose
class TimeActivity(override val contentViewId: Int = R.layout.activity_time) : BaseActivity() {

    private val serverTime = "2019-03-04T14:42:30.100"

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        initToolBar(R.string.util_time)
        initCurTimeInfo()
        initServerTimeInfo()
    }

    @SuppressLint("SetTextI18n")
    override fun initOnClick() {
        super.initOnClick()
        btnRefresh.setOnClickListener { initCurTimeInfo() }

    }


    @SuppressLint("SetTextI18n")
    private fun initServerTimeInfo() {
        tvServerYMD.text = "服务器时间转换为年月日：${serverTime.formatTimeYMD()}"
        tvServerYMDChinese.text = "服务器时间转换为年月日(汉字)：${serverTime.formatTimeYMDChinese()}"
        tvServerTime.text = "服务器时间转换为时间：${serverTime.formatTimeHM()}"
        tvServerYMDHM.text = "服务器时间转换为年月日时分：${serverTime.formatTimeYMDHM()}"
        tvServerYMDHMS.text = "服务器时间转换为年月日时分秒：${serverTime.formatTimeYMDHMS()}"
    }

    @SuppressLint("SetTextI18n")
    private fun initCurTimeInfo() {
        tvCurYear.text = "当前年份：$currentYear"
        tvCurMon.text = "当前月份：$currentMonth"
        tvCurDay.text = "当前日：$currentDay"
        tvCurMilli.text = "当前时间戳：$currentTimeMillis"
        tvCurDate.text = "当前日期：${formatCurrentDate()}"
        tvCurDateTime.text = "当前日期时间：${formatCurrentDateTime()}"
        tvCurTime.text = "当前时间：${formatCurrentTime()}"
    }
}
