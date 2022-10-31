package com.ci.act.ui.authentication.signin.model

import android.provider.Settings
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue
import java.io.Serializable
import java.util.prefs.Preferences

data class UserDetails(
    @SerializedName("address") val address: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("email_address") val emailAddress: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("is_privacy_policy_agree_required") val is_privacy_policy_agree_required: Int?,
    @SerializedName("is_sec_cnt_send_remainders") val isSecCntSendRemainders: Int?,
    @SerializedName("is_terms_conds_agree_required") val is_terms_conds_agree_required: Int?,
    @SerializedName("is_unapproved_email_address") val isUnapprovedEmailAddress: Int?,
    @SerializedName("is_unapproved_sec_cnt_email_address") val isUnapprovedSecCntEmailAddress: Int?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("law_firm_name") val lawFirmName: String?,
    @SerializedName("login_type") val loginType: String?,
    @SerializedName("off_phone_code") val offPhoneCode: String,
    @SerializedName("off_phone_number") val offPhoneNumber: String,
    @SerializedName("phone_code") val phoneCode: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("preferences") val preferences: @RawValue Preferences? = null,
    @SerializedName("profile_bio") val profileBio: String?,
    @SerializedName("profile_pic_name") val profilePicName: String?,
    @SerializedName("profile_pic_url") val profilePicUrl: String?,
    @SerializedName("sec_cnt_email_address") val secCntEmailAddress: String,
    @SerializedName("sec_cnt_name") val secCntName: String,
    @SerializedName("settings") val settings: @RawValue Settings? = null,
    @SerializedName("state") val state: String,
    @SerializedName("state_code") val stateCode: String,
    @SerializedName("state_id") val stateId: String,
    @SerializedName("tag_line") val tagLine: String?,
    @SerializedName("unapproved_email_address") val unapprovedEmailAddress: String?,
    @SerializedName("unapproved_sec_cnt_email_address") val unapprovedSecCntEmailAddress: String,
    @SerializedName("unapproved_sec_cnt_name") val unapprovedSecCntName: String,
    @SerializedName("user_unique_id") val userUniqueId: String?,
    @SerializedName("zip_code") val zipCode: String?
) : Serializable {
}
