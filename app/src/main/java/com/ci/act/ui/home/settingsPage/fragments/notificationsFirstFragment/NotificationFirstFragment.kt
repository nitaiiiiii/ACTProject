package com.ci.act.ui.home.settingsPage.fragments.notificationsFirstFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentNotificationFirstBinding

class NotificationFirstFragment :
    BaseFragment<FragmentNotificationFirstBinding, NotificationFirstView, NotificationFirstViewModel>(),
    NotificationFirstView {
    override fun getContentView(): Int = R.layout.fragment_notification_first

    override fun setViewModel(): NotificationFirstViewModel? = NotificationFirstViewModel()


    override fun getBindingVariable(): Int = BR.notificationFirstFragment

    override fun getNavigator(): NotificationFirstView = this

    override fun initViews(savedInstanceState: Bundle?) {
    }

}