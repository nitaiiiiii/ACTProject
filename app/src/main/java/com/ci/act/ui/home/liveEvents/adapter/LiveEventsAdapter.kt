package com.ci.act.ui.home.liveEvents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ci.act.R
import com.ci.act.ui.home.liveEvents.model.LiveEventModel
import com.ci.act.ui.home.subscriptions.adapter.SubscriptionViewPagerAdapter

class LiveEventsAdapter : RecyclerView.Adapter<LiveEventsAdapter.LiveEventsViewHolder>() {

    private val arrayList: ArrayList<LiveEventModel.LiveEventModelItem> = ArrayList()
    private lateinit var liveEventsClick: LiveEventsClick


    inner class LiveEventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imgBasket: ImageView? = view.findViewById(R.id.imgBasketBall)
        var txtEventsGame: TextView? = view.findViewById(R.id.txtEventsGame)
        var imgInflate: ImageView? = view.findViewById(R.id.imgInflateLocation)
        var txtInflate: TextView? = view.findViewById(R.id.txtInflateLocation)
        var eventsOne: TextView? = view.findViewById(R.id.txtLiveEventsOne)
        var registered: TextView? = view.findViewById(R.id.txtLiveEventsRegistered)
        var mainLiveEventsConstraint: ConstraintLayout? =
            view.findViewById(R.id.mainLiveEventsConstraint)


        fun liveEventsBindItems(liveEventModelItem: LiveEventModel.LiveEventModelItem) {

            txtEventsGame?.text = liveEventModelItem.eventName
            txtInflate?.text = liveEventModelItem.eventAddress
            eventsOne?.text = liveEventModelItem.evenDate
//            registered?.text = liveEventModelItem.isRegistered.toString()
            val resId: Int = itemView.context.resources.getIdentifier(
                liveEventModelItem.eventBanner,
                "drawable",
                itemView.context.packageName
            )
            val profile = ResourcesCompat.getDrawable(itemView.resources, resId, null);

            imgBasket?.let {
                Glide.with(itemView.context)
                    .load(profile)
                    .error(R.drawable.sports_basketball)
                    .into(it)
            }

            if (liveEventModelItem.isRegistered == true) {
                registered?.visibility = View.VISIBLE
            } else {
                registered?.visibility = View.GONE
            }

            mainLiveEventsConstraint?.setOnClickListener {
                liveEventsClick.liveEventsClick(liveEventModelItem)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveEventsViewHolder {
        val liveEvents =
            LayoutInflater.from(parent.context).inflate(R.layout.inflate_live_events, parent, false)
        return LiveEventsViewHolder(liveEvents)
    }

    override fun onBindViewHolder(holder: LiveEventsViewHolder, position: Int) {
        holder.liveEventsBindItems(arrayList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addArray(array: ArrayList<LiveEventModel.LiveEventModelItem>) {
        arrayList.clear()
        arrayList.addAll(array)
        notifyDataSetChanged()
    }

    fun liveEventsClick(liveEventsClick: LiveEventsClick) {
        this.liveEventsClick = liveEventsClick
    }

    interface LiveEventsClick {
        fun liveEventsClick(liveEvents: LiveEventModel.LiveEventModelItem)
    }
}