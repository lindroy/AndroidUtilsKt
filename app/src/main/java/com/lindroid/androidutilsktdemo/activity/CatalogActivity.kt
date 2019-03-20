package com.lindroid.androidutilsktdemo.activity

import android.content.Intent
import android.widget.ArrayAdapter
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.activity.statusbar.StatusBarActivity
import com.lindroid.androidutilsktdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_catalog.*

/**
 * @author Lin
 * @date 2019/2/27
 * @function 目录页
 * @Description
 */
class CatalogActivity(override val contentViewId: Int = R.layout.activity_catalog) : BaseActivity() {
    private lateinit var map: LinkedHashMap<Int, Class<*>>
    private val names: MutableList<String> = ArrayList()

    override fun initBefore() {
        super.initBefore()
        map = linkedMapOf(
            R.string.util_app_info to AppInfoActivity::class.java,
            R.string.util_brightness to BrightnessActivity::class.java,
            R.string.util_intent to IntentActivity::class.java,
            R.string.util_keyboard to KeyboardActivity::class.java,
            R.string.util_regex to RegexActivity::class.java,
            R.string.util_sdcard to SDCardActivity::class.java,
            R.string.util_screen to ScreenActivity::class.java,
            R.string.util_Spannable to SpannableActivity::class.java,
            R.string.util_status_bar to StatusBarActivity::class.java,
            R.string.util_time to TimeActivity::class.java,
            R.string.util_toast to ToastActivity::class.java,
            R.string.util_vibrator to VibratorActivity::class.java,
            R.string.util_view to ViewActivity::class.java
        )
        names.addAll(map.map {
            getString(it.key)
        })
    }

    override fun initView() {
        super.initView()
        initToolBar(title = "目录", isShowArrow = false)
        fun startActivity(cls: Class<*>?) {
            startActivity(Intent(mContext, cls))
        }
        list.adapter = ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, android.R.id.text1, names)
        list.setOnItemClickListener { parent, view, position, id ->
            val rightMap = map.filterKeys {
                getString(it) == names[position]
            }.values.toList()
            startActivity(rightMap[0])
        }

    }

}
