package com.ci.act.callbacks.model


import com.ci.act.data.BaseResponse
import com.google.gson.annotations.SerializedName

data class GenerateAccessTokenModel(
    @SerializedName("token")
    val token: String?
): BaseResponse()