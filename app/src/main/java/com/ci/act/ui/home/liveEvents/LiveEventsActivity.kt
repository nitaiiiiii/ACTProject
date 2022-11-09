package com.ci.act.ui.home.liveEvents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityLiveEventsBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.customDialogFragments.cantAccessAccount.CantAccessAccountFragment
import com.ci.act.ui.customDialogFragments.enjoyingSports.EnjoyingSportsFragment
import com.ci.act.ui.home.chargebee.ChargeBeeActivity
import com.ci.act.ui.home.eventsLive.EventsLiveActivity
import com.ci.act.ui.home.liveEvents.adapter.LiveEventsAdapter
import com.ci.act.ui.home.liveEvents.model.LiveEventModel
import com.ci.act.ui.home.mainEventScreenRegister.MainEventScreenRegisterActivity
import com.ci.act.ui.home.subscriptions.adapter.SubscriptionViewPagerAdapter
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
}