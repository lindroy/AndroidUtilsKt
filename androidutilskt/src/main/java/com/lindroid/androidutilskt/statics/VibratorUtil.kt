package com.lindroid.androidutilskt.statics

import android.Manifest.permission.VIBRATE
import android.content.Context
import android.os.Vibrator
import android.support.annotation.RequiresPermission
import com.lindroid.androidutilskt.app.AndUtil

/**
 * @author Lin
 * @date 2019/3/11
 * @function 手机振动工具类
 * @Description 待支持Android8.0的API
 *
 */
object VibratorUtil {

    /**
     * 获取Vibrator实例
     */
    val vibrator: Vibrator by lazy {
        AndUtil.appContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    /**
     * 设备是否具有振动器
     */
    val hasVibrator
        get() = vibrator.hasVibrator()

    /**
     * 开启振动
     * @param pattern: 设置振动的间歇和持续时间；每一对中的第一个值表示等待的毫秒数，第二个值表示在持续振动的毫秒数。
     * @param repeat : 重复的次数，默认为-1不重复
     */
    @RequiresPermission(VIBRATE)
    fun vibrate(vararg pattern: Long, repeat: Int = -1) {
        if (hasVibrator) {
            vibrator.vibrate(pattern, repeat)
        }
    }

    /**
     * 取消振动
     */
    @RequiresPermission(VIBRATE)
    fun cancel() {
        if (hasVibrator) {
            vibrator.cancel()
        }
    }


}