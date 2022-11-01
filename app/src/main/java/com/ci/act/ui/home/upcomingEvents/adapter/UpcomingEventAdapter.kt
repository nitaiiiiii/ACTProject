package com.ci.act.ui.home.upcomingEvents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ci.act.R
import com.ci.act.ui.home.liveEvents.model.LiveEventModel

class UpcomingEventAdapter : RecyclerView.Adapter<UpcomingEventAdapter.UpcomingEventViewHolder>() {

    private val arrayList: ArrayList<LiveEventModel.LiveEventModelItem> = ArrayList()

    class UpcomingEventViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imgUpcomingBasket: ImageView? = view.findViewById(R.id.imgUpcomingBasketBall)
        var txtUpcomingEventsGame: TextView? = view.findViewById(R.id.txtUpcomingEventsGame)
        var imgUpcomingInflate: ImageView? = view.findViewById(R.id.imgUpcomingInflateLocation)
        var txtUpcomingInflate: TextView? = view.findViewById(R.id.txtUpcomingInflateLocation)
        var eventsUpcomingOne: TextView? = view.findViewById(R.id.txtUpcomingLiveEventsOne)
        var UpcomingRegistered: TextView? = view.findViewById(R.id.txtUpcomingLiveEventsRegistered)

        fun upcomingBindItems(liveEventModelItem: LiveEventModel.LiveEventModelItem) {

            txtUpcomingEventsGame?.text = liveEventModelItem.eventName
            txtUpcomingInflate?.text = liveEventModelItem.eventAddress
            eventsUpcomingOne?.text = liveEventModelItem.evenDate
//            registered?.text = liveEventModelItem.isRegistered.toString()
            val resId: Int = itemView.context.resources.getIdentifier(
                liveEventModelItem.eventBanner,
                "drawable",
                itemView.context.packageName
            )
            val profile = ResourcesCompat.getDrawable(itemView.resources, resId, null);

            imgUpcomingBasket?.let {
                Glide.with(itemView.context)
                    .load(profile)
                    .error(R.drawable.sports_basketball)
                    .into(it)
            }

            if (liveEventModelItem.isRegistered == true) {
                UpcomingRegistered?.visibility = View.VISIBLE
            } else {
                UpcomingRegistered?.visibility = View.GONE
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingEventViewHolder {
        val liveEvents =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.inflate_upcoming_events, parent, false)
        return UpcomingEventAdapter.UpcomingEventViewHolder(liveEvents)
    }

    override fun onBindViewHolder(holder: UpcomingEventViewHolder, position: Int) {
        holder.upcomingBindItems(arrayList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addArray(array: ArrayList<LiveEventModel.LiveEventModelItem>) {
        arrayList.clear()
        arrayList.addAll(array)
        notifyDataSetChanged()
    }
}