package com.ci.act.ui.home.settingsPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySettingsPageBinding
import com.ci.act.ui.home.events.EventsActivity
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
            notifications()
        }

        mViewDataBinding?.imgLocations?.setOnClickListener {
            location()
        }

        mViewDataBinding?.imgRadius?.setOnClickListener {
            radius()
        }

        mViewDataBinding?.imgSettingsEvents?.setOnClickListener{
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun notifications() {
        mViewDataBinding?.imgNotifications?.setColorFilter(ContextCompat.getColor(this, R.color.textColor))
        mViewDataBinding?.imgLocations?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.imgRadius?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.txtNotifications?.setTextColor(ContextCompat.getColor(this, R.color.textColor))
        mViewDataBinding?.txtLocations?.setTextColor(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.txtRadius?.setTextColor(ContextCompat.getColor(this, R.color.light_grey))

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsFrameLayout, NotificationFirstFragment()).commit()

    }

    private fun location() {
        mViewDataBinding?.imgNotifications?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.imgLocations?.setColorFilter(ContextCompat.getColor(this, R.color.textColor))
        mViewDataBinding?.imgRadius?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.txtNotifications?.setTextColor(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.txtLocations?.setTextColor(ContextCompat.getColor(this, R.color.textColor))
        mViewDataBinding?.txtRadius?.setTextColor(ContextCompat.getColor(this, R.color.light_grey))

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsFrameLayout, LocationFirstFragment()).commit()
    }

    private fun radius() {
        mViewDataBinding?.imgNotifications?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.imgLocations?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.imgRadius?.setColorFilter(ContextCompat.getColor(this, R.color.textColor))
        mViewDataBinding?.txtNotifications?.setTextColor(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.txtLocations?.setTextColor(ContextCompat.getColor(this, R.color.light_grey))
        mViewDataBinding?.txtRadius?.setTextColor(ContextCompat.getColor(this, R.color.textColor))

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsFrameLayout, RadiusThirdFragment()).commit()

    }

}