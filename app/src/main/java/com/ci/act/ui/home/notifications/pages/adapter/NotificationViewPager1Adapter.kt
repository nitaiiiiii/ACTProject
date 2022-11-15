package com.ci.act.ui.home.notifications.pages.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.databinding.InflateNotificationBinding
import com.ci.act.ui.home.notifications.pages.model.NotificationsModel

class NotificationViewPager1Adapter : RecyclerView.Adapter<NotificationViewPager1Adapter.NotificationViewPager1ViewHolder>(){

    private val notificationList : ArrayList<NotificationsModel.NotificationsModelItem> = ArrayList()
    private lateinit var myClick : MyClick

    inner class NotificationViewPager1ViewHolder(val binding : InflateNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBindItems(notificationModel : NotificationsModel.NotificationsModelItem) {
            if (notificationModel.isNew) {
                binding.tvNew.visibility = View.VISIBLE
            } else {
                binding.tvNew.visibility = View.GONE
            }

            binding.tvName.text = notificationModel.name
            binding.tvMessage.text = notificationModel.message
            binding.tvTime.text = notificationModel.time
            if (notificationModel.isSelectClicked) {
                binding.tickMark.visibility = View.VISIBLE
                if (notificationModel.isSelected) {
                    binding.tickMark.setImageResource(R.drawable.ic_check_circle_main_color)
                } else {
                    binding.tickMark.setImageResource(R.drawable.ic_uncheck_circle_black)
                }
            } else {
                binding.tickMark.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewPager1ViewHolder {
        return NotificationViewPager1ViewHolder(InflateNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotificationViewPager1ViewHolder, position: Int) {
        holder.onBindItems(notificationList[holder.absoluteAdapterPosition])

        holder.binding.constraintLayout.setOnClickListener {
            myClick.myClick(notificationList[holder.absoluteAdapterPosition], holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    fun addArrayList(list : ArrayList<NotificationsModel.NotificationsModelItem>) {
        notificationList.clear()
        notificationList.addAll(list)
        notifyDataSetChanged()
    }

    fun updateList(item: NotificationsModel.NotificationsModelItem, position : Int) {
        notificationList.set(position, item)
        notifyItemChanged(position)
    }

    fun myClick(myClick : MyClick) {
        this.myClick = myClick
    }

    interface MyClick{
        fun myClick (notificationModel : NotificationsModel.NotificationsModelItem, position: Int)
    }
}