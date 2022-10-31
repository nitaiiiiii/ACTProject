package com.ci.act.ui.differentSports


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityDifferentSportsBinding
import com.ci.act.ui.authentication.accessLocation.AccessLocationActivity
import com.ci.act.ui.differentSports.adapter.DifferentSportsAdapter
import com.ci.act.ui.differentSports.model.DifferentSportsModel
import com.ci.act.ui.onboarding.OnBoardingActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DifferentSportsActivity :
    BaseActivity<ActivityDifferentSportsBinding, DifferentSportsView, DifferentSportsViewModel>(),
    DifferentSportsView {

    private var isSelectAllItems: Boolean = false
    private var isSelected: Boolean = false
    private var adapter: DifferentSportsAdapter? = null
    private var sports: ArrayList<DifferentSportsModel.DifferentSportsModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_different_sports

    override fun setViewModelClass(): Class<DifferentSportsViewModel> {
        return DifferentSportsViewModel::class.java
    }

    override fun getNavigator(): DifferentSportsView = this

    override fun getBindingVariable(): Int = BR.differentSports

    override fun initViews(savedInstanceState: Bundle?) {

        recyclerView()
        setOnClickListeners()

    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvDifferentSports)

        adapter = DifferentSportsAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<DifferentSportsModel>() {}.type
        sports = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${sports}")
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
        adapter?.addArray(sports)

        adapter?.sportsClick(object : DifferentSportsAdapter.SportsClick {
            override fun sportsClick(
                sportsModel: DifferentSportsModel.DifferentSportsModelItem,
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

    private fun setOnClickListeners() {

        mViewDataBinding?.txtNext?.setOnClickListener {
            val intent = Intent(this, AccessLocationActivity::class.java)
            startActivity(intent)
        }
        mViewDataBinding?.txtSkip?.setOnClickListener {
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }

        mViewDataBinding?.imgSelectSports?.setOnClickListener {
            updateSelectedClick()
            adapter?.addArray(sports)
        }

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