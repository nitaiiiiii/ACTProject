package com.ci.act.ui.home.eventsLive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
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
        setUpToolBar()

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


    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "LIVE EVENTS"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_back_arrow)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.light_black))
            it.imgToolBarRight.visibility = View.INVISIBLE
            it.imgToolBarShareRight.visibility = View.VISIBLE
            it.imgToolBarShareRight.setImageResource(R.drawable.ic_icon_refresh)
            it.imgToolBarShareRight.setColorFilter(ContextCompat.getColor(this, R.color.light_black))

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this, LiveEventsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}