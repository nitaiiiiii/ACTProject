package com.ci.act.ui.home.settingsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySettingsPageBinding
import com.ci.act.ui.home.settingsPage.fragments.locationSecondFragment.LocationFirstFragment
import com.ci.act.ui.home.settingsPage.fragments.notificationsFirstFragment.NotificationFirstFragment
import com.ci.act.ui.home.settingsPage.fragments.radiusThirdFragment.RadiusThirdFragment

class SettingsPageActivity :
    BaseActivity<ActivitySettingsPageBinding, SettingsPageView, SettingsPageViewModel>(),
    SettingsPageView {
    override fun getContentView(): Int = R.layout.activity_settings_page

    override fun setViewModelClass(): Class<SettingsPageViewModel> {
        return SettingsPageViewModel::class.java
    }

    override fun getNavigator(): SettingsPageView = this

    override fun getBindingVariable(): Int = BR.settingsPage

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsFrameLayout, NotificationFirstFragment()).commit()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.imgNotifications?.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentsFrameLayout, NotificationFirstFragment()).commit()
        }

        mViewDataBinding?.imgLocations?.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentsFrameLayout, LocationFirstFragment()).commit()
        }

        mViewDataBinding?.imgRadius?.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentsFrameLayout, RadiusThirdFragment()).commit()
        }
    }

}