package com.ci.act.ui.home.events

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityEventsBinding
import com.ci.act.ui.customDialogFragments.signOut.SignOutFragment
import com.ci.act.ui.home.aboutUs.AboutUsActivity
import com.ci.act.ui.home.contactUs.ContactUsActivity
import com.ci.act.ui.home.eventsLive.EventsLiveActivity
import com.ci.act.ui.home.faqEmpty.FaqEmptyActivity
import com.ci.act.ui.home.liveEvents.LiveEventsActivity
import com.ci.act.ui.home.myProfile.MyProfileActivity
import com.ci.act.ui.home.myRegisteredEvents.MyRegisteredActivity
import com.ci.act.ui.home.notifications.activity.NotificationActivity
import com.ci.act.ui.home.sendLog.SendLogActivity
import com.ci.act.ui.home.settingsPage.SettingsPageActivity
import com.ci.act.ui.home.subscriptions.SubscriptionActivity
import com.ci.act.ui.invoice.InvoicesActivity
import org.jetbrains.anko.findOptional

class EventsActivity : BaseActivity<ActivityEventsBinding, EventsView, EventsViewModel>(),
    EventsView {
    override fun getContentView(): Int = R.layout.activity_events

    override fun setViewModelClass(): Class<EventsViewModel> {
        return EventsViewModel::class.java
    }

    companion object {
        var isShowingSignOut = false
    }

    override fun getNavigator(): EventsView = this

    override fun getBindingVariable(): Int = BR.events

    override fun initViews(savedInstanceState: Bundle?) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mViewDataBinding?.navigation?.bringToFront()

        navClick()
        setOnClickListener()
        setUpToolBar()

    }

    private fun setOnClickListener() {
        mViewDataBinding?.btnMyEvents?.setOnClickListener {
            val intent = Intent(this, LiveEventsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navClick() {
        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        mViewDataBinding?.navigation?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.events -> {
                    val intent = Intent(this, MyRegisteredActivity::class.java)
                    startActivity(intent)
                }
                R.id.events1 -> {
                    val intent = Intent(this, MyProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.events2 -> {
                    val intent = Intent(this, InvoicesActivity::class.java)
                    startActivity(intent)
                }
                R.id.events3 -> {
                    val intent = Intent(this, SubscriptionActivity::class.java)
                    startActivity(intent)
                }
                R.id.events4 -> {
                    val intent = Intent(this, AboutUsActivity::class.java)
                    startActivity(intent)
                }
                R.id.events5 -> {
                    val intent = Intent(this, FaqEmptyActivity::class.java)
                    startActivity(intent)
                }
                R.id.events6 -> {
                    val intent = Intent(this, ContactUsActivity::class.java)
                    startActivity(intent)
                }
                R.id.events7 -> {
                    val intent = Intent(this, SendLogActivity::class.java)
                    startActivity(intent)
                }
                R.id.events8 -> {

                    val intent = Intent(this, NotificationActivity::class.java)
                    startActivity(intent)
//                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,NotificationsFragment()).commit()
                }
                R.id.events9 -> {
                    val intent = Intent(this, SettingsPageActivity::class.java)
                    startActivity(intent)
                }

                R.id.events10 -> {
                    val signOutFragment = SignOutFragment()
                    if (!EventsActivity.isShowingSignOut) {
                        EventsActivity.isShowingSignOut = true
                        supportFragmentManager?.let { it1 ->
                            signOutFragment.show(
                                it1,
                                "Cancel Project"
                            )
                        }
                    }
                }
            }
            true
        }
    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.txtToolbarHeading?.text = "EVENTS"
        mViewDataBinding?.toolBar?.txtToolBarDummyIcon?.visibility = View.INVISIBLE
        mViewDataBinding?.toolBar?.imgToolBarLeft?.visibility = View.INVISIBLE
        mViewDataBinding?.toolBar?.txtToolbarHeading?.textAlignment = View.TEXT_ALIGNMENT_CENTER


        mViewDataBinding?.toolBar?.imgToolBarRight?.setOnClickListener {
            val drawerLayout = this.findOptional<DrawerLayout>(R.id.mainDrawerLayout)
            if (drawerLayout?.isDrawerOpen(GravityCompat.END) == true) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout?.openDrawer(GravityCompat.END)
            }
        }
    }


}