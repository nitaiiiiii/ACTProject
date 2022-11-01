package com.ci.act.ui.mysports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMySportsBinding
import com.ci.act.ui.authentication.accessLocation.AccessLocationActivity
import com.ci.act.ui.differentSports.adapter.DifferentSportsAdapter
import com.ci.act.ui.differentSports.model.DifferentSportsModel
import com.ci.act.ui.mysports.adapter.MySportsAdapter
import com.ci.act.ui.mysports.model.MySportsModel
import com.ci.act.ui.onboarding.OnBoardingActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySportsActivity:BaseActivity<ActivityMySportsBinding,MySportsView,MySportsViewModel>(),MySportsView {

    private var isSelectAllItems: Boolean = false
    private var isSelected: Boolean = false
    private var adapter: MySportsAdapter? = null
    private var sports: ArrayList<MySportsModel.MySportsModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_my_sports

    override fun setViewModelClass(): Class<MySportsViewModel> {
        return MySportsViewModel::class.java
    }

    override fun getNavigator(): MySportsView = this

    override fun getBindingVariable(): Int = BR.mySports

    override fun initViews(savedInstanceState: Bundle?) {
        recyclerView()
        setOnClickListeners()
    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvDifferentSports)

        adapter = MySportsAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<MySportsModel>() {}.type
        sports = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${sports}")
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
        adapter?.addArray(sports)

        adapter?.sportsClick(object : MySportsAdapter.SportsClick {
            override fun sportsClick(
                sportsModel: MySportsModel.MySportsModelItem,
                position: Int
            ) {
                for (i in sports.indices) {
                    if (sports[i].id == sportsModel.id) {
                        sports[i].isSelected = !sports[i].isSelected
//                        adapter.addList(jsonArray)
                        adapter?.updateList(sports[i], position)
                        updateSelected()
                    }
                }
            }


        })
    }

    private fun setOnClickListeners() {

        mViewDataBinding?.imgSelectSports?.setOnClickListener {
            updateSelectedClick()
            adapter?.addArray(sports)
        }

    }


    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString = this.assets.open("sportsList.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun updateSelectedClick() {
        if (isSelectAllItems) {
            for (i in sports.indices) {
                sports[i].isSelected = false
            }
            mViewDataBinding?.imgSelectSports?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.radio_button_unchecked
                )
            )
            isSelectAllItems = false
        } else {
            for (i in sports.indices) {
                sports[i].isSelected = true
            }
            mViewDataBinding?.imgSelectSports?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.check_circle
                )
            )
            isSelectAllItems = true
        }
    }

    private fun updateSelected() {
        for (i in sports.indices) {
            if (sports[i].isSelected) {
                isSelectAllItems = true
            } else {
                isSelectAllItems = false
                break
            }
        }

        if (isSelectAllItems) {
            mViewDataBinding?.imgSelectSports?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.check_circle
                )
            )
        } else {
            mViewDataBinding?.imgSelectSports?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.radio_button_unchecked
                )
            )
        }
    }

}