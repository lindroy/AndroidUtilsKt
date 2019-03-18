package com.lindroid.androidutilsktdemo.activity

import android.util.Log
import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.lindroid.androidutilsktdemo.receiver.SCREEN_TAG
import com.lindroid.androidutilsktdemo.receiver.ScreenActionReceiver
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_screen.*


/**
 * @author Lin
 * @date 2019/2/27
 * @function 屏幕相关工具类
 * @Description
 */

@EnableDragToClose
class ScreenActivity(override val contentViewId: Int = R.layout.activity_screen) : BaseActivity() {
    private lateinit var screenReceiver: ScreenActionReceiver

    override fun initBefore() {
        super.initBefore()
        screenReceiver = ScreenActionReceiver(mContext)

    }

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_screen)

        screenReceiver.registerScreenActionReceiver()
        //分辨率
        tvResolution.text = "屏幕宽高:${getScreenWidth()}x${getScreenHeight()}"
        //屏幕密度
        tvDensity.text = "屏幕密度:${getScreenDensity()}"
        //Dpi
        tvDpi.text = "屏幕Dpi:${getScreenDPI()}"
        //屏幕方向
        tvOrientation.text = when (isLandscape) {
            true -> "屏幕方向:横屏"
            false -> "屏幕方向:竖屏"
        }
    }

    override fun initOnClick() {
        super.initOnClick()
        btnPortrait.setOnClickListener { setScreenPortrait() }
        btnLandscape.setOnClickListener { setScreenLandscape() }
    }

    override fun initOnListener() {
        super.initOnListener()
        //监听屏幕点亮和解锁
        screenReceiver.setScreenActionListener(object : ScreenActionReceiver.ScreenActionListener {
            override fun onScreenOn() {
                Log.d(SCREEN_TAG, "亮屏")
            }

            override fun onScreenOff() {
                Log.d(SCREEN_TAG, "暗屏")
            }

            override fun onScreenUnLocked() {
                Log.d(SCREEN_TAG, "解锁")
            }
        })
        setKeepScreenOn(swScreenOn.isChecked)
        swScreenOn.setOnCheckedChangeListener { buttonView, isChecked ->
            shortToast(if (isChecked) "已设置屏幕长亮" else "已取消屏幕长亮")
            setKeepScreenOn(isChecked)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        screenReceiver.unRegisterScreenActionReceiver()
    }


}
