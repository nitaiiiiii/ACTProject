package com.ci.act.ui.mysports.model


import com.google.gson.annotations.SerializedName

class MySportsModel : ArrayList<MySportsModel.MySportsModelItem>(){
    data class MySportsModelItem(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("sportsName")
        var sportsName: String?,
        @SerializedName("sportsimage")
        var sportsimage: String?,

        var isSelected : Boolean = false

    )
}