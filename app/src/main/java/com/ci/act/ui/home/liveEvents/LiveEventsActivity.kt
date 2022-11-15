package com.ci.act.ui.home.liveEvents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityLiveEventsBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.customDialogFragments.cantAccessAccount.CantAccessAccountFragment
import com.ci.act.ui.customDialogFragments.enjoyingSports.EnjoyingSportsFragment
import com.ci.act.ui.customDialogFragments.signOut.SignOutFragment
import com.ci.act.ui.home.aboutUs.AboutUsActivity
import com.ci.act.ui.home.chargebee.ChargeBeeActivity
import com.ci.act.ui.home.contactUs.ContactUsActivity
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.eventsLive.EventsLiveActivity
import com.ci.act.ui.home.faqEmpty.FaqEmptyActivity
import com.ci.act.ui.home.liveEvents.adapter.LiveEventsAdapter
import com.ci.act.ui.home.liveEvents.model.LiveEventModel
import com.ci.act.ui.home.mainEventScreenRegister.MainEventScreenRegisterActivity
import com.ci.act.ui.home.myProfile.MyProfileActivity
import com.ci.act.ui.home.myRegisteredEvents.MyRegisteredActivity
import com.ci.act.ui.home.sendLog.SendLogActivity
import com.ci.act.ui.home.settingsPage.SettingsPageActivity
import com.ci.act.ui.home.subscriptions.SubscriptionActivity
import com.ci.act.ui.home.subscriptions.adapter.SubscriptionViewPagerAdapter
import com.ci.act.ui.invoice.InvoicesActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LiveEventsActivity :
    BaseActivity<ActivityLiveEventsBinding, LiveEventsView, LiveEventsViewModel>(), LiveEventsView {

    private var adapter: LiveEventsAdapter? = null
    private var live: ArrayList<LiveEventModel.LiveEventModelItem> = ArrayList()


    override fun getContentView(): Int = R.layout.activity_live_events

    override fun setViewModelClass(): Class<LiveEventsViewModel> {
        return LiveEventsViewModel::class.java

    }

    companion object {
        var isShowingEnjoyingSportsDialog = false
    }

    override fun getNavigator(): LiveEventsView = this

    override fun getBindingVariable(): Int = BR.liveEvents

    override fun initViews(savedInstanceState: Bundle?) {
        recyclerView()
        setOnClickListener()
        adapterClick()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mViewDataBinding?.navigation?.bringToFront()

        mViewDataBinding?.imgEvents?.setOnClickListener {

            mViewDataBinding?.mainDrawerLayout?.openDrawer(Gravity.RIGHT)

        }


        navClick()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.liveEvents?.setOnClickListener {
            val intent = Intent(this, EventsLiveActivity::class.java)
            startActivity(intent)
        }

        mViewDataBinding?.btnLiveEvents?.setOnClickListener {
            val enjoyingSportsFragment = EnjoyingSportsFragment()
            if (!LiveEventsActivity.isShowingEnjoyingSportsDialog ) {
                LiveEventsActivity.isShowingEnjoyingSportsDialog  = true
                supportFragmentManager?.let { it1 ->
                    enjoyingSportsFragment.show(
                        it1,
                        "Enjoying Sports"
                    )
                }
            }
        }
    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvLiveEvents)

        adapter = LiveEventsAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<LiveEventModel>() {}.type
        live = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${live}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter?.addArray(live)

    }

    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString = this.assets.open("liveEvents.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun adapterClick() {

        adapter?.liveEventsClick(object : LiveEventsAdapter.LiveEventsClick {
            override fun liveEventsClick(liveEvents: LiveEventModel.LiveEventModelItem) {
                val intent =
                    Intent(this@LiveEventsActivity, MainEventScreenRegisterActivity::class.java)
                startActivity(intent)
            }
        })
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

}