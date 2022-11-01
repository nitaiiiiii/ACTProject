package com.ci.act.ui.home.notifications.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.home.notifications.model.NotificationsModel

class SecondAdapter : RecyclerView.Adapter<SecondAdapter.SecondViewHolder>() {

    private val arrayList: ArrayList<NotificationsModel.NotificationsModelItem> = ArrayList()

    class SecondViewHolder(secondView: View) : RecyclerView.ViewHolder(secondView) {
        val imgSecondMainNotification: ImageView? =
            secondView.findViewById(R.id.imgSecondMainNotification)
        val txtSecondMainNotification: TextView? =
            secondView.findViewById(R.id.txtSecondMainNotification)
        val txtHeadingMainNotification: TextView? =
            secondView.findViewById(R.id.txtHeadingMainNotification)
        val imgLikedComment: ImageView? = secondView.findViewById(R.id.imgLikedComment)
        val txtLikedComment: TextView? = secondView.findViewById(R.id.txtLikedComment)
        val txtMinutesAgo: TextView? = secondView.findViewById(R.id.txtMinutesAgo)

        fun secondBindItems(notificationsModelItem: NotificationsModel.NotificationsModelItem) {
            txtHeadingMainNotification?.text = notificationsModelItem.name
            txtLikedComment?.text = notificationsModelItem.message
            txtMinutesAgo?.text = notificationsModelItem.time


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        val secondNotification =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.inflate_second_main_notification, parent, false)
        return SecondAdapter.SecondViewHolder(secondNotification)
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        holder.secondBindItems(arrayList[holder.absoluteAdapterPosition])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}