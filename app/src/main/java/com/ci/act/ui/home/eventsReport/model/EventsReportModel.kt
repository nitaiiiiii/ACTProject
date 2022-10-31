package com.ci.act.ui.home.eventsReport.model


import com.google.gson.annotations.SerializedName

class EventsReportModel : ArrayList<EventsReportModel.EventsReportModelItem>(){
    data class EventsReportModelItem(
        @SerializedName("event_name")
        var eventName: String?,
        @SerializedName("rank")
        var rank: String?,
        @SerializedName("rank_sup_text")
        var rankSupText: String?,
        @SerializedName("test_1_name")
        var test1Name: String?,
        @SerializedName("test_1_score")
        var test1Score: String?,
        @SerializedName("test_2_name")
        var test2Name: String?,
        @SerializedName("test_2_score")
        var test2Score: String?,
        @SerializedName("total_test")
        var totalTest: String?
    )
}