package com.lindroid.androidutilskt.extension.logcat.logstrategy

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * @author Lin
 * @date 2019/9/4
 * @function 将日志输出成CSV格式
 * @Description
 */
class DiskLogStrategy(private val handler:Handler) : LogStrategy {

    override fun log(level: Int, tag: String?, message: String) {
        handler.sendMessage(handler.obtainMessage(level,message))
    }

    class WriteHandler(looper: Looper, private var folder: String, private var maxFileSize: Int) : Handler(looper) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val content = when (msg) {
                null -> ""
                else -> msg.obj as String
            }
            var fileWriter: FileWriter? = null
            val logFile = getLogFile(folder,"Log")
            try {
                fileWriter = FileWriter(logFile, true)
                fileWriter.append(content)

                fileWriter.flush()
                fileWriter.close()
            } catch (e: IOException) {
                if (fileWriter != null) {
                    try {
                        fileWriter.flush()
                        fileWriter.close()
                    } catch (e1: IOException) { /* fail silently */
                    }
                }
            }

        }

        private fun getLogFile(folderName: String, fileName: String): File {
            val folder = File(folderName)
            if (!folder.exists()){
                folder.mkdirs()
            }
            var newFileCount = 0
            var newFile = File(folder,String.format("%s_%s.csv", fileName, newFileCount))
            var existingFile:File? = null
            while (newFile.exists()){
                existingFile = newFile
                newFileCount++
                newFile = File(folder,String.format("%s_%s.csv", fileName, newFileCount))
            }
            if(existingFile != null){
                if (existingFile.length() >= maxFileSize){
                    return newFile
                }
                return existingFile
            }
            return newFile
        }
    }
}