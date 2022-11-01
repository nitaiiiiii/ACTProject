package com.ci.act.debugging

import android.annotation.SuppressLint
import com.ci.act.MyApplication
import com.ci.act.R
import com.ci.act.util.DeviceInfo
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*


class MyApplicationLog private constructor() {

    companion object {
        private var myApplicationLog: MyApplicationLog? = null
        fun getInstance(): MyApplicationLog {
            return myApplicationLog ?: MyApplicationLog()
        }
    }

    var logListener: LogListener? = null

    @SuppressLint("SimpleDateFormat")
    val ft: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")

    fun info(title: String, logMessage: String) {
        updateLogFile("$title : $logMessage", "Info")
    }

    fun enteredInfo(title: String, logMessage: String) {
        updateLogFile("User entered $title", "Info")
    }

    fun buttonClickedInfo(title: String, buttonName: String) {
        updateLogFile("User tapped on  $buttonName in $title", "Info")
    }

    fun selectionItemInfo(title: String, itemName: String) {
        updateLogFile("User selected  $itemName in $title", "Info")
    }

    fun apiRequest(title: String, request: String) {
        updateLogFile(request, "API Request")
    }

    fun apiResponse(title: String, apiResponse: String) {
        updateLogFile(apiResponse, "API Response")
    }

    fun error(title: String, logMessage: String) {
        updateLogFile("$title :: $logMessage", "Error")
    }

    private fun updateLogFile(logMessage: String, logType: String) {
//        Log.e("Stored Value",logMessage)
        doAsync {
            var file = getLogFile()
            var out: PrintWriter? = null
            try {
                // Get length of file in bytes
                val fileSizeInBytes = file.length()
                // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                val fileSizeInKB = fileSizeInBytes / 1024
                // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                val fileSizeInMB = fileSizeInKB / 1024

                if (fileSizeInMB >= 5) {
                    file.delete()
                    file = getLogFile()
                }
                out = PrintWriter(BufferedWriter(FileWriter(file.absolutePath, true)))
                val dNow = Date()
                if (fileSizeInBytes <= 0) {
                    out.println("${ft.format(dNow)}  :  Device Info  --->  ${DeviceInfo.getDevice()}\n")
                }
                out.println("${ft.format(dNow)}  :  $logType  --->  $logMessage")
                out.println("\n\r")
                logListener?.let { listener ->
                    uiThread {
                        listener.onLogUpdated()
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                out?.close()
            }
        }
    }

    private fun getLogFile(): File {
        val logFile = File(getRootFolderPath(), logFileName())
        if (!logFile.exists()) {
            logFile.createNewFile()
        }
        return logFile
    }

    private fun getRootFolderPath(): String {
//        val root = android.os.Environment.getExternalStorageDirectory()
        val folder = File(
            MyApplication.getApplicationContext().filesDir,
            MyApplication.getApplicationContext().resources.getString(R.string.log_folder_name)
        )
//        val folder =
//            File("${root.absoluteFile}", SHC.applicationContext().resources.getString(R.string.log_folder_name))
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return folder.absolutePath
    }

    private fun logFileName(): String =
        MyApplication.getApplicationContext().resources.getString(R.string.log_file_name)

    fun deleteFile() {
        val file = File(getRootFolderPath(), logFileName())
        if (file.exists()) {
            file.delete()
        }
    }

    interface LogListener {
        fun onLogUpdated()
    }
}