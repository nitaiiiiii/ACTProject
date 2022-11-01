package com.ci.act.ui.home.eventsLive.model


import com.google.gson.annotations.SerializedName

class EventsLiveModel : ArrayList<EventsLiveModel.EventsLiveModelItem>() {
    data class EventsLiveModelItem(
        @SerializedName("even_date")
        var evenDate: String?,
        @SerializedName("event_address")
        var eventAddress: String?,
        @SerializedName("event_banner")
        var eventBanner: String?,
        @SerializedName("event_location")
        var eventLocation: String?,
        @SerializedName("event_name")
        var eventName: String?,
        @SerializedName("event_participants")
        var eventParticipants: String?,
        @SerializedName("event_state")
        var eventState: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("is_liked")
        var isLiked: Boolean?,
        @SerializedName("is_registered")
        var isRegistered: Boolean?,
        @SerializedName("likes")
        var likes: String?,
        @SerializedName("pin")
        var pin: String?
    )
}