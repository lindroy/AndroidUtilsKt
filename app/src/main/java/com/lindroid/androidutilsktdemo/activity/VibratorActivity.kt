package com.lindroid.androidutilsktdemo.activity

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_vibrator.*


/**
 * @author Lin
 * @date 2019/3/10
 * @function 手机振动工具类
 * @Description
 * https://developer.android.google.cn/reference/android/os/VibrationEffect.html
 * https://www.jianshu.com/p/38fde766cc03
 * https://www.jianshu.com/p/921d24ba1974
 * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/lib/src/main/java/com/blankj/utilcode/util/VibrateUtils.java
 */
class VibratorActivity(override val contentViewId: Int= com.lindroid.androidutilsktdemo.R.layout.activity_vibrator) : BaseActivity() {
    private lateinit var vibrator:Vibrator

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_vibrator)
         vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        Log.e("VibratorActivity","是否有振动器：${vibrator.hasVibrator()}")
    }


    override fun initOnClick() {
        super.initOnClick()
        btnStart.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26){
                val ve = VibrationEffect.createOneShot(2000,VibrationEffect.DEFAULT_AMPLITUDE)
                vibrator.vibrate(ve)
            }else{
                //数组中的整数用来打开或关闭振动器，第一个值表示在打开振动器之前要等待的毫秒数下一个值表示在关闭振动器之前保持振动器的毫秒数，随后的值交替执行。
                vibrator.vibrate(longArrayOf(1000,2000,3000,5000),-1)
            }

        }
        btnCancel.setOnClickListener {
            vibrator.cancel()
        }
    }
}
