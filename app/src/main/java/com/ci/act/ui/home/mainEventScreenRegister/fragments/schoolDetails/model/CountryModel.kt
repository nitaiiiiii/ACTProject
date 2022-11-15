package com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.model


import com.google.gson.annotations.SerializedName

class CountryModel : ArrayList<CountryModel.CountryModelItem>(){
    data class CountryModelItem(
        @SerializedName("countryName")
        var countryName: String?,
        @SerializedName("id")
        var id: String?
    )
}