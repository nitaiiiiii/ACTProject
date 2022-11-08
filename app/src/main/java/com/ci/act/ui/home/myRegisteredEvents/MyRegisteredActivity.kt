package com.ci.act.ui.home.myRegisteredEvents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMyRegisteredBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.eventsReport.EventsReportActivity
import com.ci.act.ui.home.myRegisteredEvents.adapter.MyRegisteredAdapter
import com.ci.act.ui.home.myRegisteredEvents.model.RegisteredModel
import com.ci.act.ui.home.sportsBoard.SportsBoardActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyRegisteredActivity :
    BaseActivity<ActivityMyRegisteredBinding, MyRegisteredView, MyRegisteredViewModel>(),
    MyRegisteredView {

    private var adapterRegistered: MyRegisteredAdapter? = null
    private var eventsRegistered: ArrayList<RegisteredModel.RegisteredModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_my_registered

    override fun setViewModelClass(): Class<MyRegisteredViewModel> {
        return MyRegisteredViewModel::class.java
    }

    override fun getNavigator(): MyRegisteredView = this

    override fun getBindingVariable(): Int = BR.myRegisteredEvents

    override fun initViews(savedInstanceState: Bundle?) {

        recyclerView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.imgRegisteredEvents?.setOnClickListener {
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }
    }


    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvMyRegistered)

        adapterRegistered = MyRegisteredAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<RegisteredModel>() {}.type
        eventsRegistered = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${eventsRegistered}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapterRegistered
        adapterRegistered?.addArray(eventsRegistered[0].eventsList)

        adapterRegistered?.myClick(object : MyRegisteredAdapter.MyClick{
            override fun myClick(event: RegisteredModel.RegisteredModelItem.Events) {
                val intent = Intent(applicationContext,SportsBoardActivity::class.java)
                startActivity(intent)

            }

            override fun myLeaderboardClick(event: RegisteredModel.RegisteredModelItem.Events) {
                val intent = Intent(applicationContext,EventsReportActivity::class.java)
                startActivity(intent)
            }
        })

    }

    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString =
                this.assets.open("events_registered.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }


}