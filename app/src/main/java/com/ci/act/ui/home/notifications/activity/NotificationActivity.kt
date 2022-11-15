package com.ci.act.ui.home.notifications.activity

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityNotificationBinding
import com.ci.act.ui.home.notifications.NotificationsFragment
import com.ci.act.ui.home.notifications.pages.page1.NotificationViewPagerFragment

class NotificationActivity : BaseActivity<ActivityNotificationBinding, NotificationView, NotificationViewModel>(),
    NotificationView {
    override fun getContentView(): Int = R.layout.activity_notification

    override fun setViewModelClass(): Class<NotificationViewModel> {
        return NotificationViewModel::class.java

    }

    override fun getNavigator(): NotificationView = this

    override fun getBindingVariable(): Int = BR.notificationActivity

    override fun initViews(savedInstanceState: Bundle?) {


        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,
            NotificationsFragment()).commit()


    }
}