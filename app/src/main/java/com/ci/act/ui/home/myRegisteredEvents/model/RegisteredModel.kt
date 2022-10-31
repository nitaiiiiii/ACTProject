package com.ci.act.ui.home.myRegisteredEvents.model


import com.google.gson.annotations.SerializedName

class RegisteredModel : ArrayList<RegisteredModel.RegisteredModelItem>(){
    data class RegisteredModelItem(
        @SerializedName("eventsList")
        var eventsList: ArrayList<Events>,
        @SerializedName("participated")
        var participated: String?,
        @SerializedName("registered")
        var registered: String?
    ) {
        data class Events(
            @SerializedName("days_left")
            var daysLeft: String?,
            @SerializedName("event_date")
            var eventDate: String?,
            @SerializedName("event_location")
            var eventLocation: String?,
            @SerializedName("event_name")
            var eventName: String?,
            @SerializedName("is_event_completed")
            var isEventCompleted: Boolean?,
            @SerializedName("logo")
            var logo: String?,
            @SerializedName("rank")
            var rank: String?,
            @SerializedName("sub_script")
            var subScript: String?,
            @SerializedName("total_participated")
            var totalParticipated: String?
        )
    }
}