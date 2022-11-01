package com.ci.act.ui.home.notifications.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.home.notifications.model.NotificationsModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val arrayList: ArrayList<NotificationsModel.NotificationsModelItem> = ArrayList()

    class MainViewHolder(mainView: View) : RecyclerView.ViewHolder(mainView) {
        var txtDateNotification: TextView? = mainView.findViewById(R.id.txtDateNotification)
        var txtSelectAllNotification: TextView? =
            mainView.findViewById(R.id.txtSelectAllNotification)

        fun mainBindItems(notificationsModelItem: NotificationsModel.NotificationsModelItem) {
            txtDateNotification?.text = notificationsModelItem.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val mainNotification =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.inflate_main_notification, parent, false)
        return MainAdapter.MainViewHolder(mainNotification)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.mainBindItems(arrayList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}