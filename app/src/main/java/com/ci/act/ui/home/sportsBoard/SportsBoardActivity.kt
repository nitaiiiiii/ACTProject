package com.ci.act.ui.home.sportsBoard

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySportsBoardBinding
import com.ci.act.ui.home.sportsBoard.adapter.SportsBoardAdapter
import com.ci.act.ui.home.sportsBoard.model.SportsBoardModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SportsBoardActivity :
    BaseActivity<ActivitySportsBoardBinding, SportsBoardView, SportsBoardViewModel>(),
    SportsBoardView {

    private var adapterSportsBoard: SportsBoardAdapter? = null
    private var eventsSportsBoard: ArrayList<SportsBoardModel.SportsBoardModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_sports_board

    override fun setViewModelClass(): Class<SportsBoardViewModel> {
        return SportsBoardViewModel::class.java
    }

    override fun getNavigator(): SportsBoardView = this

    override fun getBindingVariable(): Int = BR.sportsBoard

    override fun initViews(savedInstanceState: Bundle?) {
        recyclerView()
    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvSportsBoard)

        adapterSportsBoard = SportsBoardAdapter()
        val sportsBoardJsom = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<SportsBoardModel>() {}.type
        eventsSportsBoard = gson.fromJson(sportsBoardJsom, listMoviesType)
        Log.e("hello", "${eventsSportsBoard}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapterSportsBoard
        adapterSportsBoard?.addArray(eventsSportsBoard)

    }

    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString =
                this.assets.open("sports_board.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}