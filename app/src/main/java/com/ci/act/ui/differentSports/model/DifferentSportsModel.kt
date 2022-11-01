package com.ci.act.ui.differentSports.model


import com.google.gson.annotations.SerializedName

class DifferentSportsModel : ArrayList<DifferentSportsModel.DifferentSportsModelItem>() {
    data class DifferentSportsModelItem(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("sportsName")
        var sportsName: String?,
        @SerializedName("sportsimage")
        var sportsimage: String?,

        var isSelected: Boolean = false
    )
}