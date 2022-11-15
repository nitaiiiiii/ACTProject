package com.ci.act.ui.home.notifications.pages.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.databinding.InflateNotificationParentBinding
import com.ci.act.ui.home.notifications.pages.model.NotificationsModel

class NotificationViewPager1ParentAdapter : RecyclerView.Adapter<NotificationViewPager1ParentAdapter.NotificationViewPager1ParentViewHolder>(){

    private val notificationList : ArrayList<NotificationsModel.NotificationsModelItem> = ArrayList()
    private val notificationDateList : ArrayList<NotificationsModel.NotificationsModelItem> = ArrayList()

    inner class NotificationViewPager1ParentViewHolder(val binding : InflateNotificationParentBinding) : RecyclerView.ViewHolder(binding.root) {
        private var isSelectAllItems : Boolean = false
        fun onBindItems(notificationModel : NotificationsModel.NotificationsModelItem) {
            val adapter = NotificationViewPager1Adapter()
            binding.tvDate.text = notificationModel.date
            binding.rvNotification.layoutManager = LinearLayoutManager(itemView.context)
            binding.rvNotification.adapter = adapter
            val arrayList = ArrayList<NotificationsModel.NotificationsModelItem>()
            for (i in notificationList){
                if (i.date == notificationModel.date){
                    arrayList.add(i)
                }
            }

            val newTemp = arrayList.sortedWith(compareBy<NotificationsModel.NotificationsModelItem> {it.date }.thenBy{it.date})
            arrayList.clear()
            arrayList.addAll(newTemp)
            adapter.addArrayList(arrayList)

            if (notificationModel.isSelectClicked) {
                binding.tvSelectAll.visibility = View.VISIBLE
            } else {
                binding.tvSelectAll.visibility = View.GONE
            }

            binding.tvSelectAll?.setOnClickListener{
                if (isSelectAllItems) {
                    for (i in arrayList.indices) {
                        arrayList[i].isSelected = false
                    }
                    binding.tvSelectAll.text = "Select All"
                    isSelectAllItems = false
                } else {
                    for (i in arrayList.indices) {
                        arrayList[i].isSelected = true
                    }
                    binding.tvSelectAll.text = "Unselect All"
                    isSelectAllItems = true
                }
                val newTemp = arrayList.sortedWith(compareBy<NotificationsModel.NotificationsModelItem> {it.date }.thenBy{it.date})
                arrayList.clear()
                arrayList.addAll(newTemp)
                adapter.addArrayList(arrayList)
            }

            adapter.myClick(object : NotificationViewPager1Adapter.MyClick{
                override fun myClick(notificationModel:NotificationsModel.NotificationsModelItem, position: Int) {
                    for (i in arrayList.indices) {
                        if (arrayList[i].id == notificationModel.id) {
                            arrayList[i].isSelected = !arrayList[i].isSelected
                            adapter.updateList(arrayList[i], position)
                            for (i in arrayList.indices) {
                                if(arrayList[i].isSelected) {
                                    isSelectAllItems = true
                                } else {
                                    isSelectAllItems = false
                                    break
                                }
                            }
                            if (isSelectAllItems) {
                                binding.tvSelectAll.text = "Unselect All"
                            } else {
                                binding.tvSelectAll.text = "Select All"
                            }
                        }
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewPager1ParentViewHolder {
        return NotificationViewPager1ParentViewHolder(InflateNotificationParentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotificationViewPager1ParentViewHolder, position: Int) {
        holder.onBindItems(notificationDateList[holder.absoluteAdapterPosition])
    }

    override fun getItemCount(): Int {
        return notificationDateList.size
    }

    fun addArrayList(list : ArrayList<NotificationsModel.NotificationsModelItem>, dateList : ArrayList<NotificationsModel.NotificationsModelItem>) {
        notificationList.clear()
        notificationList.addAll(list)

        notificationDateList.clear()
        notificationDateList.addAll(dateList)
        notifyDataSetChanged()
    }
}