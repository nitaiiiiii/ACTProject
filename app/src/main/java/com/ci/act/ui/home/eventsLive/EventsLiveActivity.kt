package com.ci.act.ui.home.eventsLive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityEventsLiveBinding
import com.ci.act.ui.home.eventsLive.adapter.EventsLiveAdapter
import com.ci.act.ui.home.eventsLive.model.EventsLiveModel
import com.ci.act.ui.home.liveEvents.LiveEventsActivity
import com.ci.act.ui.home.upcomingEvents.UpcomingEventsActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EventsLiveActivity :
    BaseActivity<ActivityEventsLiveBinding, EventsLiveView, EventsLiveViewModel>(), EventsLiveView {

    private var adapter: EventsLiveAdapter? = null
    private var events: ArrayList<EventsLiveModel.EventsLiveModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_events_live

    override fun setViewModelClass(): Class<EventsLiveViewModel> {
        return EventsLiveViewModel::class.java
    }

    override fun getNavigator(): EventsLiveView = this

    override fun getBindingVariable(): Int = BR.eventsLive

    override fun initViews(savedInstanceState: Bundle?) {
        recyclerView()
        setOnClickListeners()

    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvUpcomingLiveEvents)

        adapter = EventsLiveAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<EventsLiveModel>() {}.type
        events = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${events}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter?.addArray(events)

    }

    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString = this.assets.open("eventsLive.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun setOnClickListeners() {

        mViewDataBinding?.imgLiveActiveEvents?.setOnClickListener {
            val intent = Intent(this, LiveEventsActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}