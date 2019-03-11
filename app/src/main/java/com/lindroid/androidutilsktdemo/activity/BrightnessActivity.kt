package com.lindroid.androidutilsktdemo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.SeekBar
import com.lindroid.androidutilskt.extension.isAutoBrightness
import com.lindroid.androidutilskt.extension.systemBrightness
import com.lindroid.androidutilskt.extension.windowBrightness
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_brightness.*

/**
 * @author Lin
 * @date 2019/3/9
 * @function 亮度工具类
 * @Description
 */

private const val RQ_WIRTE_SETTINGS = 100
class BrightnessActivity(override val contentViewId: Int = R.layout.activity_brightness) : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        initToolBar(R.string.util_brightness)
        tvAutoBright.text = "是否是自动亮度：${if (isAutoBrightness) "是" else "否"}"
        tvWindowBright.text = "当前窗口亮度=$windowBrightness"
        sbWindowBright.progress = if (windowBrightness > 0) (windowBrightness * 100).toInt() else 0
        sbWindowBright.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                windowBrightness = progress.toFloat() / 100F
                tvWindowBright.text = "当前窗口亮度=$windowBrightness"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        tvSystemBright.text = "系统亮度=$systemBrightness"
        sbSystemBright.progress = ((systemBrightness.toFloat() / 255) * 100).toInt()

        //修改系统屏幕亮度需要修改系统设置的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果当前平台版本大于23平台
            if (!Settings.System.canWrite(mContext)) {
                val intent = with(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)) {
                    data = Uri.parse("package:$packageName")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    this
                }
                startActivityForResult(intent, RQ_WIRTE_SETTINGS)
            } else {
                sbSystemBright.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        systemBrightness = progress * 255 / 100
                        tvSystemBright.text = "系统亮度=$systemBrightness"
                    }


                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }


                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }

                })
            }
        } else {

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        Log.e("Tag","设置成功requestCode：$requestCode")
        Log.e("Tag","设置成功resultCode：$resultCode")
        Log.e("Tag","是否授权Settings.System.canWrite(mContext)+：${Settings.System.canWrite(mContext)}")


    }

}
