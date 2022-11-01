package com.ci.act.ui.home.faqList.model


import com.google.gson.annotations.SerializedName

class FaqListModel : ArrayList<FaqListModel.FaqListModelItem>() {
    data class FaqListModelItem(
        @SerializedName("answer")
        var answer: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("question_name")
        var questionName: String?
    )
}