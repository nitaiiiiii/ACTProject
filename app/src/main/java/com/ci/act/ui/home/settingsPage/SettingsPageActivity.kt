package com.ci.act.ui.home.settingsPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
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
        setUpToolBar()
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

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "SETTINGS"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_back_arrow)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.light_black))
            it.imgToolBarRight.visibility = View.INVISIBLE

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)
            }
        }
    }

}