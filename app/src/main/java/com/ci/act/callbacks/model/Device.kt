package com.ci.act.callbacks.model


import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("device_fcm_token")
    val deviceFcmToken: String?,
    @SerializedName("device_key")
    val deviceKey: String?,
    @SerializedName("device_manufacture")
    val deviceManufacture: String?,
    @SerializedName("device_model")
    val deviceModel: String?,
    @SerializedName("device_os")
    val deviceOs: String?,
    @SerializedName("device_os_version")
    val deviceOsVersion: String?,
    @SerializedName("ip_address")
    val ipAddress: String?,
    @SerializedName("locale")
    val locale: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("time_zone")
    val timeZone: String?
)
