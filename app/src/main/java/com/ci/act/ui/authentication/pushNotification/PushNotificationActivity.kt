package com.ci.act.ui.authentication.pushNotification

import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityPushNotificationBinding
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.ui.home.myZeroRegisteredEvents.MyZeroRegisteredActivity
import com.ci.act.ui.onboarding.OnBoardingActivity

class PushNotificationActivity :
    BaseActivity<ActivityPushNotificationBinding, PushNotificationView, PushNotificationViewModel>(),
    PushNotificationView {
    override fun getContentView(): Int = R.layout.activity_push_notification
    override fun setViewModelClass(): Class<PushNotificationViewModel> {
        return PushNotificationViewModel::class.java
    }

    override fun getNavigator(): PushNotificationView = this

    override fun getBindingVariable(): Int = BR.pushNotification

    override fun initViews(savedInstanceState: Bundle?) {
         setOnClickListener()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.btnPushNotification?.setOnClickListener {
            PreferenceHelper.getInstance().finishPushNotification()
            val intent1 = Intent(this, MyZeroRegisteredActivity::class.java)
            startActivity(intent1)
            finish()
        }
        mViewDataBinding?.txtSignInEmailSignUpScreen?.setOnClickListener {
            PreferenceHelper.getInstance().finishPushNotification()
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}