package com.ci.act.ui.home.eventsLive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ci.act.R
import com.ci.act.ui.home.eventsLive.model.EventsLiveModel


class EventsLiveAdapter : RecyclerView.Adapter<EventsLiveAdapter.EventsLiveViewHolder>() {

    private val arrayList1: ArrayList<EventsLiveModel.EventsLiveModelItem> = ArrayList()


    class EventsLiveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgEvents: ImageView? = view.findViewById(R.id.imgEventsLiveStream)
        var imgCamp: ImageView? = view.findViewById(R.id.imgFootBallCamp)

        var imgGroups: ImageView? = view.findViewById(R.id.imgFootBallCampGroups)
        var txtFootball: TextView? = view.findViewById(R.id.txtFootBallCamp)
        var txtCountry: TextView? = view.findViewById(R.id.txtFootBallCountry)
        var txtParticipants: TextView? = view.findViewById(R.id.txtFootBallCountryParticipants)
        var txtParticipated: TextView? = view.findViewById(R.id.txtFootBallCountryParticipated)
        var txtParticipatedLikes: TextView? =
            view.findViewById(R.id.txtFootBallCountryParticipantsLikes)
        var imgBall: ImageView? = view.findViewById(R.id.imgFootBallCampFavorites)


        fun eventsBindItem(eventsLiveModelItem: EventsLiveModel.EventsLiveModelItem) {
            txtFootball?.text = eventsLiveModelItem.eventName
            txtCountry?.text = eventsLiveModelItem.eventState
            txtParticipants?.text = eventsLiveModelItem.eventParticipants
            txtParticipatedLikes?.text = eventsLiveModelItem.likes

            val resId: Int = itemView.context.resources.getIdentifier(
                eventsLiveModelItem.eventBanner,
                "drawable",
                itemView.context.packageName
            )
            val profile = ResourcesCompat.getDrawable(itemView.resources, resId, null);
            Glide.with(imgEvents!!)
                .load(profile)
                .error(R.drawable.profile_picture)
                .into(imgEvents!!)

            if (eventsLiveModelItem.isLiked == true) {
                imgBall?.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
            } else {
                imgBall?.setBackgroundResource(R.drawable.favorite)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsLiveViewHolder {
        val Events =
            LayoutInflater.from(parent.context).inflate(R.layout.inflate_events_live, parent, false)
        return EventsLiveAdapter.EventsLiveViewHolder(Events)
    }

    override fun onBindViewHolder(holder: EventsLiveViewHolder, position: Int) {
        holder.eventsBindItem(arrayList1[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return arrayList1.size
    }


    fun addArray(array: ArrayList<EventsLiveModel.EventsLiveModelItem>) {
        arrayList1.clear()
        arrayList1.addAll(array)
        notifyDataSetChanged()
    }


}