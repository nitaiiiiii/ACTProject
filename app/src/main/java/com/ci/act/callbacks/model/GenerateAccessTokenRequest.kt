package com.ci.act.callbacks.model


import com.ci.act.util.DeviceInfo
import com.google.gson.annotations.SerializedName

data class GenerateAccessTokenRequest(
    @SerializedName("device")
    val device: Device? = DeviceInfo.getDevice()
)