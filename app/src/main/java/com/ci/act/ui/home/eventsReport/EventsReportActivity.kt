package com.ci.act.ui.home.eventsReport


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
import com.ci.act.databinding.ActivityEventsReportBinding
import com.ci.act.ui.home.eventsReport.adapter.EventsReportAdapter
import com.ci.act.ui.home.eventsReport.model.EventsReportModel
import com.ci.act.ui.home.myProfile.MyProfileActivity
import com.ci.act.ui.home.myRegisteredEvents.MyRegisteredActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EventsReportActivity :
    BaseActivity<ActivityEventsReportBinding, EventsReportView, EventsReportViewModel>(),
    EventsReportView {

    private var reportsAdapter: EventsReportAdapter? = null
    private var reportLive: ArrayList<EventsReportModel.EventsReportModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_events_report

    override fun setViewModelClass(): Class<EventsReportViewModel> {
        return EventsReportViewModel::class.java
    }

    override fun getNavigator(): EventsReportView = this

    override fun getBindingVariable(): Int = BR.eventsReport

    override fun initViews(savedInstanceState: Bundle?) {
        recyclerView()
        setUpToolBar()

    }


    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvMyEventsReport)

        reportsAdapter = EventsReportAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<EventsReportModel>() {}.type
        reportLive = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${reportLive}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = reportsAdapter
        reportsAdapter?.addArray(reportLive)

    }

    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString =
                this.assets.open("events_report.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "EVENT REPORT"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_back_arrow)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.light_black))
            it.imgToolBarRight.visibility = View.INVISIBLE
            it.imgToolBarShareRight.visibility = View.VISIBLE

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this,MyRegisteredActivity::class.java)
                startActivity(intent)
            }
        }
    }


}