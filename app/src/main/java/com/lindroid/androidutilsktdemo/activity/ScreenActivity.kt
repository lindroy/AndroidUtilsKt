package com.lindroid.androidutilsktdemo.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.util.Log
import com.lindroid.androidutilskt.extension.*
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.lindroid.androidutilsktdemo.receiver.SCREEN_TAG
import com.lindroid.androidutilsktdemo.receiver.ScreenActionReceiver
import kotlinx.android.synthetic.main.activity_screen.*


/**
 * @author Lin
 * @date 2019/2/27
 * @function 屏幕相关工具类
 * @Description
 */
private const val RQ_WRITE_SETTINGS = 100

//@EnableDragToClose
class ScreenActivity(override val contentViewId: Int = com.lindroid.androidutilsktdemo.R.layout.activity_screen) :
    BaseActivity() {
    private lateinit var screenReceiver: ScreenActionReceiver
    private lateinit var lockTimeMap: LinkedHashMap<Int, String>

    override fun initBefore() {
        super.initBefore()
        screenReceiver = ScreenActionReceiver(mContext)
        lockTimeMap = linkedMapOf(
            -1 to "跟随系统",
            5000 to "5秒",
            15000 to "15秒",
            30000 to "30秒",
            60000 to "1分钟",
            120000 to "2分钟",
            0 to "永不"
        )

    }

    override fun initView() {
        super.initView()
        initToolBar(com.lindroid.androidutilsktdemo.R.string.util_screen)
        screenReceiver.registerScreenActionReceiver()

        //分辨率
        tvResolution.text = "屏幕宽高:${screenWidth}x${screenHeight}"
        //屏幕密度
        tvDensity.text = "屏幕密度:$screenDensity"
        //Dpi
        tvDpi.text = "屏幕Dpi:$screenDPI"
        //屏幕方向
        tvOrientation.text = when (isLandscape) {
            true -> "屏幕方向:横屏"
            false -> "屏幕方向:竖屏"
        }
        tvLockTime.text = "${getScreenAutoLockTime() / 1000}秒"
    }

    override fun initOnClick() {
        super.initOnClick()
        btnPortrait.setOnClickListener { setScreenPortrait() }
        btnLandscape.setOnClickListener { setScreenLandscape() }
        flLockTime.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //如果当前平台版本大于23平台
                if (!Settings.System.canWrite(mContext)) {
                    val intent = with(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)) {
                        data = Uri.parse("package:$packageName")
                        this
                    }
                    startActivityForResult(intent, RQ_WRITE_SETTINGS)
                } else {
                    showAutoLockTimeDialog()
                }
            } else {
                showAutoLockTimeDialog()
            }
        }
    }

    /**
     * 选择自动锁屏时间对话框
     */
    private fun showAutoLockTimeDialog() {
        var lockTime = 0
        val builder = with(AlertDialog.Builder(mContext)) {
            setTitle("选择自动锁屏时间")
            setSingleChoiceItems(lockTimeMap.values.toTypedArray(), 0) { dialog, which ->
                lockTime = lockTimeMap.keys.toList()[which]
            }
            setPositiveButton("确认") { dialog, which ->
                when (lockTime) {
                    0 -> Log.d(SCREEN_TAG, "设置永不自动锁屏：${setScreenAutoLockNever()}")
                    -1 -> Log.d(SCREEN_TAG, "设置跟随系统：${setScreenAutoLockTime(getScreenAutoLockTime())}")
                    else -> Log.d(SCREEN_TAG, "设置自动锁屏时间：${setScreenAutoLockTime(lockTime)}")
                }
                tvLockTime.text = lockTimeMap[lockTime]
                dialog.dismiss()
            }
            setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
            this
        }
        builder.show()
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
        isKeepScreenOn = swScreenOn.isChecked
        swScreenOn.setOnCheckedChangeListener { buttonView, isChecked ->
            shortToast(if (isChecked) "已设置屏幕长亮" else "已取消屏幕长亮")
            isKeepScreenOn = isChecked
        }
        isFullScreen = swFullScreen.isChecked
        swFullScreen.setOnCheckedChangeListener { buttonView, isChecked ->
            shortToast(if (isChecked) "已设置全屏" else "已取消全屏")
            isFullScreen = isChecked
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RQ_WRITE_SETTINGS -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.System.canWrite(mContext)) {
                        shortToast(R.string.permission_granted)
                        showAutoLockTimeDialog()
                    } else {
                        shortToast(R.string.permission_refuse)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        screenReceiver.unRegisterScreenActionReceiver()
    }


}
