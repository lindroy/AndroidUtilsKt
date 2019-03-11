package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.statics.IntentUtil
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.lindroid.androidutilsktdemo.util.pmCallPhone
import com.lindroid.androidutilsktdemo.util.pmCamera
import com.lindroid.androidutilsktdemo.util.pmSendSMS
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_intent.*

/**
 * @author Lin
 * @date 2018/11/5
 * @function 意图工具类
 * @Description
 */
@EnableDragToClose
class IntentActivity(override val contentViewId: Int = R.layout.activity_intent) : BaseActivity() {

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_intent)
    }

    override fun initOnClick() {
        super.initOnClick()
        //打开系统设置
        btnSystemSetting.setOnClickListener {
            IntentUtil.launchSystemSetting(mContext)
        }
        //打开wifi设置
        btnWiFiSetting.setOnClickListener {
            IntentUtil.launchWifiSetting(mContext)
        }
        //打开拨号面板
        btnDialPage.setOnClickListener {
            IntentUtil.launchDialPage(mContext)
        }
        //直接拨打电话
        btnCallPhone.setOnClickListener {
            pmCallPhone {
                if (it) {
                    IntentUtil.callPhone(mContext, "1008611")
                }
            }
        }
        //打开浏览器
        btnBrowser.setOnClickListener {
            IntentUtil.launchBrowse(mContext, "http://www.baidu.com/") {
                if (it) {
                    shortToast("成功打开浏览器选择面板")
                } else {
                    shortToast("打开浏览器选择面板失败")
                }
            }
        }
        //启动相机
        btnCamera.setOnClickListener {
            pmCamera {
                IntentUtil.launchCamera(this, 100)
            }
        }
        //发送短信
        btnSendSMS.setOnClickListener {
            pmSendSMS {
                IntentUtil.sendSMS(mContext, "10086", "这是一条短信")
            }
        }
    }
}
