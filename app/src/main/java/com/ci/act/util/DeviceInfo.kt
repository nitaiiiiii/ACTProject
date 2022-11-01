package com.ci.act.util

import android.annotation.SuppressLint
import android.provider.Settings
import com.ci.act.MyApplication
import com.ci.act.callbacks.model.Device
import java.text.SimpleDateFormat
import java.util.*

class DeviceInfo {
    companion object {

        val queryMap = HashMap<String, String>().apply {
            put("source", "android")
            put("device_key", getDeviceKey())
            put("locale", "en")
        }

        fun getDevice(): Device {
            return Device(
                getFCMToken(),
                getDeviceKey(),
                getDeviceManufacture(),
                getDeviceModel(),
                "android",
                getDeviceOsVersion(),
                "",
                "en",
                "android",
                getDeviceTimeZone()
            )
        }

        private fun getFCMToken(): String? {
            return ""
        }

        @SuppressLint("HardwareIds")
        fun getDeviceKey(): String {
            return Settings.Secure.getString(
                MyApplication.getApplicationContext().contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }

        fun getDeviceManufacture(): String {
            return android.os.Build.MANUFACTURER
        }

        fun getDeviceModel(): String {
            return android.os.Build.MODEL
        }

        fun getDeviceOsVersion(): String {
            return android.os.Build.VERSION.SDK_INT.toString()
        }

        @SuppressLint("SimpleDateFormat")
        fun getDeviceTimeZone(): String {
            val tz = SimpleDateFormat("Z").format(Date())
            return TimeZone.getDefault().id + "::" + tz.substring(0, 3) + ":" + tz.substring(3, 5)
        }
    }
}