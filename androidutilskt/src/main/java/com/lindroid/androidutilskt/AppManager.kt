package com.lindroid.androidutilskt

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * @author Lin
 * @date 2018/10/23
 * @function App管理器
 * @Description
 */

object AppManager {
    private val activityStack: Stack<Activity> = Stack()

    /**
     * Activity入栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * Activity出栈
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    /**
    * 获取当前栈顶Activity
    */
    fun currentActivity(): Activity = activityStack.lastElement()

    /**
     * 清理栈中所有的Activity
     */
    fun finishAllActivity() {
        activityStack.forEach {
            it.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}