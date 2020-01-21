package com.lindroid.androidutilsktdemo.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.lindroid.androidutilskt.extension.logcat.buildLogConfig
import com.lindroid.androidutilskt.statics.AppManager
import com.lindroid.androidutilsktdemo.R
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * @author Lin
 * @date 2019/2/27
 * @function 基类Activity
 * @Description
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mContext: Context

    //默认值为0
    abstract val contentViewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        AppManager.addActivity(this)
        initBefore()
        setContentView(contentViewId)
        initView()
        initOnListener()
        buildLogConfig {

        }
    }

    open fun initOnListener() {
        initOnClick()
    }


    open fun initOnClick() {

    }

    open fun initBefore() {

    }

    open fun initView() {

    }


    fun initToolBar(
        title: String = getString(R.string.app_name),
        isShowArrow: Boolean = true, @ColorRes toolBarColor: Int = R.color.colorPrimary
    ) {
        val toolView = window.decorView
        toolView.toolBar.title = title
        toolView.toolBar.setBackgroundColor(ContextCompat.getColor(mContext, toolBarColor))
        //ToolBar的属性设置要在setSupportActionBar方法之前调用
        setSupportActionBar(toolView.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isShowArrow)
    }

    fun initToolBar(@StringRes stringId: Int, isShowArrow: Boolean = true) {
        initToolBar(mContext.getString(stringId), isShowArrow)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.removeActivity(this)
    }

}