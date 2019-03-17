package com.lindroid.androidutilsktdemo.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.lindroid.androidutilskt.extension.isScreenLocked
import com.lindroid.androidutilskt.extension.isScreenOff
import com.lindroid.androidutilskt.extension.isScreenOn
import com.lindroid.androidutilskt.extension.isScreenUnlocked

private const val SCREEN_TAG = "ScreenTag"

class ScreenActionReceiver(private val mContext: Context) : BroadcastReceiver() {
    private var isRegistered = false

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            Intent.ACTION_SCREEN_ON -> {
                //开屏
                Log.d(SCREEN_TAG, "亮屏")
            }
            Intent.ACTION_SCREEN_OFF -> {
                //锁屏
                Log.d(SCREEN_TAG, "锁屏")

            }
            Intent.ACTION_USER_PRESENT -> {
                //解屏
                Log.d(SCREEN_TAG, "解屏")

            }
        }
        Log.d(SCREEN_TAG, "isScreenLocked：$isScreenLocked")
        Log.d(SCREEN_TAG, "isScreenUnlocked：$isScreenUnlocked")
        Log.d(SCREEN_TAG, "isScreenOn：$isScreenOn")
        Log.d(SCREEN_TAG, "isScreenOff：$isScreenOff")
    }

    fun registerScreenActionReceiver() {
        if (!isRegistered) {
            Log.d(SCREEN_TAG, "注册锁屏监听广播")
            isRegistered = true
            mContext.registerReceiver(this, with(IntentFilter()) {
                addAction(Intent.ACTION_SCREEN_ON)
                addAction(Intent.ACTION_SCREEN_OFF)
                addAction(Intent.ACTION_USER_PRESENT)
                this
            })
        }
    }

    fun unRegisterScreenActionReceiver() {
        Log.d(SCREEN_TAG, "解绑锁屏监听广播")
        if (isRegistered) {
            isRegistered = false
            mContext.unregisterReceiver(this)
        }
    }
}
