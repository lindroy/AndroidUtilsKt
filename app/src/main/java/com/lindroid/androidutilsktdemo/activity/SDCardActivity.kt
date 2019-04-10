package com.lindroid.androidutilsktdemo.activity

import com.lindroid.androidutilskt.extension.isSDCardMounted
import com.lindroid.androidutilskt.extension.sdCardAvailableSize
import com.lindroid.androidutilskt.extension.sdCardPath
import com.lindroid.androidutilskt.extension.sdCardTotalSize
import com.lindroid.androidutilsktdemo.R
import com.lindroid.androidutilsktdemo.base.BaseActivity
import com.youngfeng.snake.annotations.EnableDragToClose
import kotlinx.android.synthetic.main.activity_sdcard.*

/**
 * @author Lin
 * @date 2019/3/19
 * @function SD卡工具类
 * @Description
 */
@EnableDragToClose
class SDCardActivity(override val contentViewId: Int = R.layout.activity_sdcard) : BaseActivity() {

    override fun initView() {
        super.initView()
        initToolBar(R.string.util_sdcard)
        tvMounted.text = "SD卡是否可用：$isSDCardMounted"
        tvPath.text = "SD卡路径：${sdCardPath}"
        tvTotalSize.text = "SD卡的总大小：${sdCardTotalSize / (1024 * 1024 * 1024)}G"
        tvAvailableSize.text = "SD卡可用空间大小：${sdCardAvailableSize / (1024 * 1024 * 1024)}G"
    }
}
