package com.ci.act.ui.home.sportsBoard.model


import com.google.gson.annotations.SerializedName

class SportsBoardModel : ArrayList<SportsBoardModel.SportsBoardModelItem>(){
    data class SportsBoardModelItem(
        @SerializedName("profile_image")
        var profileImage: String?,
        @SerializedName("rank")
        var rank: String?,
        @SerializedName("time")
        var time: String?,
        @SerializedName("user_id")
        var userId: String?,
        @SerializedName("user_name")
        var userName: String?
    )
}