package com.ci.act.ui.authentication.accessLocation

import android.content.Intent
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityAccessLocationBinding
import com.ci.act.ui.authentication.pushNotification.PushNotificationActivity
import com.ci.act.ui.differentSports.DifferentSportsActivity
import com.ci.act.ui.onboarding.OnBoardingActivity

class AccessLocationActivity :
    BaseActivity<ActivityAccessLocationBinding, AccessLocationView, AccessLocationViewModel>(),
    AccessLocationView {
    override fun getContentView(): Int = R.layout.activity_access_location

    override fun setViewModelClass(): Class<AccessLocationViewModel> {
        return AccessLocationViewModel::class.java
    }

    override fun getNavigator(): AccessLocationView = this

    override fun getBindingVariable(): Int = BR.accessLocation

    override fun initViews(savedInstanceState: Bundle?) {

        setOnClickListeners()

    }

    private fun setOnClickListeners() {

        mViewDataBinding?.imgBackButton?.setOnClickListener {
            val intent = Intent(this, DifferentSportsActivity::class.java)
            startActivity(intent)
            finish()
        }
        mViewDataBinding?.txtSkipAccess?.setOnClickListener {
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }
        mViewDataBinding?.btnAccessLocation?.setOnClickListener {
            val intent = Intent(this, PushNotificationActivity::class.java)
            startActivity(intent)
        }

    }


}