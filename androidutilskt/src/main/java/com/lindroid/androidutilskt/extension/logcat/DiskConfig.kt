package com.lindroid.androidutilskt.extension.logcat

import android.os.Environment
import android.os.HandlerThread
import android.util.Log
import com.lindroid.androidutilskt.extension.logcat.formatstrategy.CsvFormatStrategy
import com.lindroid.androidutilskt.extension.logcat.logadapter.DiskLogAdapter
import com.lindroid.androidutilskt.extension.logcat.logstrategy.DiskLogStrategy
import com.lindroid.androidutilskt.extension.logcat.logstrategy.LogStrategy
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Lin
 * @date 2019/9/3
 * @function 输入日志到磁盘的配置
 * @Description
 */
const val MAX_BYTES = 500 * 1024 //每个文件的容量，

class DiskConfig constructor() {
    internal var folderName = "AndLog" //存储日志的目录名称
    internal var tag = "LogUtil"
    internal var date = Date()
    internal var dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.CHINA)
    internal var logStrategy: LogStrategy? = null
    private var init: (DiskConfig.() -> Unit)? = null

    fun setFolderName(folderName: String) = this.also { it.folderName = folderName }

    fun setTag(tag: String) = this.also { it.tag = tag }

    fun setDate(date: Date) = this.also { it.date = date }

    fun setDateFormat(format: SimpleDateFormat) = this.apply { dateFormat = format }

    constructor(init: (DiskConfig.() -> Unit)? = null) : this() {
        init?.run {
            this()
            this@DiskConfig.init = init
            addDiskConfig()
        }
    }

    /**
     * 如果是以Lambda的形式设置，则可以不必调用build()方法
     */
    fun build() {
        if (init == null) {
            addDiskConfig()
        }
    }

    private fun addDiskConfig() {
        addLogAdapter(object : DiskLogAdapter(createFormatStrategy()) {

        })
    }

    internal fun createFormatStrategy(): CsvFormatStrategy {
        if (logStrategy == null) {
            //存储在根目录下
            val diskPath = Environment.getExternalStorageDirectory().absolutePath
            val folder = "$diskPath${File.separatorChar}$folderName"
            Log.e("Tag","folder:$folder")
            val ht = HandlerThread("AndroidFileLogger.$folder")
            ht.start()
            val handler = DiskLogStrategy.WriteHandler(ht.looper, folder, MAX_BYTES)
            logStrategy = DiskLogStrategy(handler)
        }
        return CsvFormatStrategy(this)
    }
}
