package com.ci.act.ui.home.notifications.pages.model


import com.google.gson.annotations.SerializedName

class NotificationsModel : ArrayList<NotificationsModel.NotificationsModelItem>(){
    data class NotificationsModelItem(
        @SerializedName("date")
        var date: String,
        @SerializedName("icon")
        var icon: String,
        @SerializedName("id")
        var id: String,
        @SerializedName("is_new")
        var isNew: Boolean,
        @SerializedName("message")
        var message: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("time")
        var time: String,

        var isSelected : Boolean = false,
        var isSelectClicked : Boolean = false
    )
}