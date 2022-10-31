package com.ci.act.ui.authentication.pushNotification

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityPushNotificationBinding

class PushNotificationActivity :BaseActivity<ActivityPushNotificationBinding,PushNotificationView,PushNotificationViewModel>(),PushNotificationView{
    override fun getContentView(): Int = R.layout.activity_push_notification
    override fun setViewModelClass(): Class<PushNotificationViewModel> {
        return PushNotificationViewModel::class.java
    }

    override fun getNavigator(): PushNotificationView = this

    override fun getBindingVariable(): Int = BR.pushNotification

    override fun initViews(savedInstanceState: Bundle?) {

    }

}