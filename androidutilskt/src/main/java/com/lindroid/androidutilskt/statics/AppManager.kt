package com.lindroid.androidutilskt.statics

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.ArrayMap
import kotlin.system.exitProcess

/**
 * @author Lin
 * @date 2018/10/23
 * @function App管理器
 * @Description
 */

object AppManager {

//    private val activityStack: Stack<Activity> = Stack()

     val activitySet: ArrayMap<String, Activity> = ArrayMap()

    /**
     * 当前Activity标记
     */
    private var curTag = ""

    /**
     * Activity入栈
     */
    @JvmStatic
    fun addActivity(activity: Activity) {
//        activityStack.add(activity)
        curTag = getObjectTag(activity)
        activitySet[curTag] = activity

    }

    /**
     * Activity出栈
     */
    @JvmStatic
    fun removeActivity(activity: Activity) = activitySet.remove(getObjectTag(activity))

    /**
     * Activity出栈
     */
    fun removeActivity(vararg clazz:Class<*>){
        clazz.forEach {clz->
            activitySet.forEach{
                if (it.value.javaClass == clz){
                    removeActivity(it.value)
                }
            }
        }
    }

    /**
     * 销毁一个Activity并出栈
     */

    @JvmStatic
    fun finishActivity(activity: Activity): Activity? {
        if (!activity.isDestroyed) {
            activity.finish()
        }
//        activityStack.remove(activity)
        return removeActivity(activity)
    }

    /**
     * 销毁Activity并出栈
     */
    @JvmStatic
    fun finishActivity(vararg clazz:Class<*>){
        val keys = activitySet.keys
        clazz.forEach {clz->
            keys.forEach{
                val activity = activitySet[it]
                if (activity!=null && activity.javaClass == clz){
                    finishActivity(activity)
                }
            }
        }

    }

    /**
     * 销毁除了给定的Activity外的所有Activity
     * @param clazz:Activity白名单
     */
    @JvmStatic
    fun finishExceptActivity(vararg clazz:Class<*>){
        val keys = activitySet.keys
        clazz.forEach { clz->
            keys.forEach {
                val activity = activitySet[it]
                if (activity!= null && activity.javaClass != clz){
                    finishActivity(activity)
                }
            }
        }
    }

    /**
     * 获取当前栈顶Activity
     * @return 如果栈内元素为空，则返回null
     */
    @JvmStatic
    fun currentActivity(): Activity? =
        if (activitySet.isNotEmpty()) activitySet[curTag] else null
//        if (activityStack.isNotEmpty()) activityStack.lastElement() else null

    /**
     * 清理栈中所有的Activity
     */
    @JvmStatic
    fun finishAllActivity() {
       /* activityStack.forEach {
            it.finish()
        }
        activityStack.clear()*/
        activitySet.forEach{
            if (!it.value.isDestroyed){
                it.value.finish()
            }
        }
        activitySet.clear()
    }

    /**
     * 退出应用程序
     */
    @JvmStatic
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        exitProcess(0)
    }

    /**
     * 设置栈中Activity的key
     */
    private fun getObjectTag(activity: Activity) =
        activity.javaClass.name + Integer.toHexString(activity.hashCode())
}