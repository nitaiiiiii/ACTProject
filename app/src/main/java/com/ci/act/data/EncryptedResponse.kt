package com.ci.act.data


import com.google.gson.annotations.SerializedName

open class EncryptedResponse() {
    @SerializedName("edata")
    var data: String? = null
}