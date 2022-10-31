package com.ci.act.data

import com.google.gson.annotations.SerializedName

open class BaseResponse: EncryptedResponse() {
    @SerializedName("valid")
    var valid: Boolean = false
    @SerializedName("message")
    var message: String? = null
    @SerializedName("force_logout")
    var forceLogout: Int = 0
    @SerializedName("is_expired")
    var isExpired: Int = 0
    @SerializedName("is_email_not_registered")
    var isEmailNotRegistered: Int = 0
    @SerializedName("is_account_disabled")
    var isAccountDisabled: Int = 0
    @SerializedName("is_account_deleted")
    var isAccountDeleted: Int = 0
}