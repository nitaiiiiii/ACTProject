package com.ci.act.ui.home.notifications.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ci.act.ui.home.notifications.pages.page1.NotificationViewPagerFragment

class NotificationViewPagerAdapter(fm : FragmentManager, lc : Lifecycle) :  FragmentStateAdapter(fm, lc){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> NotificationViewPagerFragment()
            1 -> NotificationViewPagerFragment()
            2 -> NotificationViewPagerFragment()
            3 -> NotificationViewPagerFragment()
            else -> NotificationViewPagerFragment()
        }
    }
}