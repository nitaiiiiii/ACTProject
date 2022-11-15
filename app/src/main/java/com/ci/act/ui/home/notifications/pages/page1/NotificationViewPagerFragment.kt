package com.ci.act.ui.home.notifications.pages.page1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentNotificationViewPagerBinding
import com.ci.act.ui.home.notifications.NotificationsViewModel
import com.ci.act.ui.home.notifications.pages.adapter.NotificationViewPager1ParentAdapter
import com.ci.act.ui.home.notifications.pages.model.NotificationsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NotificationViewPagerFragment : BaseFragment<FragmentNotificationViewPagerBinding,NotificationViewPagerView,NotificationViewPagerViewModel>(), NotificationViewPagerView{

    private lateinit var adapter : NotificationViewPager1ParentAdapter
    private var notificationArray = ArrayList<NotificationsModel.NotificationsModelItem>()
    private val sharedSelectViewModel : NotificationsViewModel by activityViewModels()
    override fun getContentView(): Int = R.layout.fragment_notification_view_pager

    override fun setViewModel(): NotificationViewPagerViewModel = NotificationViewPagerViewModel()

    override fun getBindingVariable(): Int = BR.notificationViewPager


    override fun getNavigator(): NotificationViewPagerView = this

    override fun initViews(savedInstanceState: Bundle?) {
        mViewDataBinding?.layoutSelect?.visibility = View.GONE
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        addJsonArray()
        adapter = NotificationViewPager1ParentAdapter()
        mViewDataBinding?.rvNotification?.layoutManager = LinearLayoutManager(requireContext())
        mViewDataBinding?.rvNotification?.adapter = adapter

        val newList = ArrayList<NotificationsModel.NotificationsModelItem>()
        val temp = notificationArray.groupBy { it.date }.entries.map { it.value.maxBy { it.date }} as ArrayList<NotificationsModel.NotificationsModelItem>
//        val newTemp = temp.sortedWith(compareBy<NotificationModel.NotificationModelItem> {it.date }.thenBy{it.date})
        newList.addAll(temp)
        adapter.addArrayList(notificationArray, newList)
    }

    private fun addJsonArray() {
        val jsonString = getJsonData()
        if (jsonString != null) {
            val jsonType = object : TypeToken<NotificationsModel>() {}.type
            val gson = Gson()
            notificationArray = gson.fromJson(jsonString, jsonType)
            Log.e("json-model", "${notificationArray.size}")
        }
    }

    private fun getJsonData(): String? {
        var jsonString: String? = null
        return try {
            jsonString = requireContext().assets.open("notification_json.json").bufferedReader().use { it.readText() }
            jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            jsonString
        }
    }

    override fun addObservables() {
        sharedSelectViewModel.getSelect().observe(viewLifecycleOwner, Observer { isSelectClicked ->
            for (i in notificationArray.indices) {
                notificationArray[i].isSelectClicked = isSelectClicked
            }
            val newList = ArrayList<NotificationsModel.NotificationsModelItem>()
            val temp = notificationArray.groupBy { it.date }.entries.map { it.value.maxBy { it.date }} as ArrayList<NotificationsModel.NotificationsModelItem>
            newList.addAll(temp)
            adapter.addArrayList(notificationArray, newList)

//            mViewDataBinding?.layoutSelect?.isVisible = isSelectClicked
            if (isSelectClicked) {
                mViewDataBinding?.layoutSelect?.visibility = View.VISIBLE
            } else {
                mViewDataBinding?.layoutSelect?.visibility = View.GONE
            }
        })
    }

}