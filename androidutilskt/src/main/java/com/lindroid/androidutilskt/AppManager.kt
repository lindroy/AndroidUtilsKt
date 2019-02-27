package com.lindroid.androidutilskt

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * @author Lin
 * @date 2018/10/23
 * @function Activity管理器
 * @Description
 */
//class AppManager private constructor(){
//    private val activityStack: Stack<Activity> = Stack()
//
//    companion object {
//        //单例模式
//        val instance: AppManager by lazy { AppManager() }
//    }
//    /**
//     * Activity入栈
//     */
//    fun addActivity(activity: Activity){
//        activityStack.add(activity)
//    }
//
//    /**
//     * Activity出栈
//     */
//    fun finishActivity(activity: Activity) {
//        activity.finish()
//        activityStack.remove(activity)
//    }
//
//    /**
//     * 获取当前栈顶Activity
//     */
//    fun currentActivity():Activity = activityStack.lastElement()
//
//    /**
//     * 清理栈
//     */
//    fun finishAllActivity(){
//        activityStack.forEach {
//            it.finish()
//        }
//        activityStack.clear()
//    }
//
//    /**
//     * 退出应用程序
//     */
//    fun exitApp(context: Context){
//        finishAllActivity()
//        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        activityManager.killBackgroundProcesses(context.packageName)
//        System.exit(0)
//    }
//
//}


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
     * 清理栈
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