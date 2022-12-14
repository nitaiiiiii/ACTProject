package com.ci.act.ui.home.upcomingEvents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.BR
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityUpcomingEventsBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.liveEvents.LiveEventsActivity
import com.ci.act.ui.home.liveEvents.model.LiveEventModel
import com.ci.act.ui.home.upcomingEvents.adapter.UpcomingEventAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UpcomingEventsActivity :
    BaseActivity<ActivityUpcomingEventsBinding, UpcomingEventsView, UpcomingEventsViewModel>(),
    UpcomingEventsView {

    private var adapter: UpcomingEventAdapter? = null
    private var upcoming: ArrayList<LiveEventModel.LiveEventModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_upcoming_events
    override fun setViewModelClass(): Class<UpcomingEventsViewModel> {
        return UpcomingEventsViewModel::class.java
    }

    override fun getNavigator(): UpcomingEventsView = this

    override fun getBindingVariable(): Int = BR.upcomingEvents

    override fun initViews(savedInstanceState: Bundle?) {

        recyclerView()
        setOnClickListeners()
        setUpToolBar()
    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvUpcomingLiveEvents)

        adapter = UpcomingEventAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<LiveEventModel>() {}.type
        upcoming = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${upcoming}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter?.addArray(upcoming)

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

    private fun setOnClickListeners() {

    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "UPCOMING EVENTS"
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