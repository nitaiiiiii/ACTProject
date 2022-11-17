package com.ci.act.ui.mysports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMySportsBinding
import com.ci.act.ui.authentication.accessLocation.AccessLocationActivity
import com.ci.act.ui.differentSports.adapter.DifferentSportsAdapter
import com.ci.act.ui.differentSports.model.DifferentSportsModel
import com.ci.act.ui.home.myProfile.MyProfileActivity
import com.ci.act.ui.mysports.adapter.MySportsAdapter
import com.ci.act.ui.mysports.model.MySportsModel
import com.ci.act.ui.onboarding.OnBoardingActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySportsActivity : BaseActivity<ActivityMySportsBinding, MySportsView, MySportsViewModel>(),
    MySportsView {

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
        setUpToolBar()
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
            mViewDataBinding?.toolBar?.imgToolBarRight?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.radio_button_unchecked
                )
            )
            mViewDataBinding?.toolBar?.imgToolBarRight?.visibility = View.INVISIBLE
            mViewDataBinding?.toolBar?.txtToolBarSelectSport?.text = "Select All"
            isSelectAllItems = false
        } else {
            for (i in sports.indices) {
                sports[i].isSelected = true
            }
            mViewDataBinding?.toolBar?.imgToolBarRight?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.check_circle
                )
            )
            mViewDataBinding?.toolBar?.txtToolBarSelectSport?.text = "Unselect All"
            mViewDataBinding?.toolBar?.imgToolBarRight?.visibility = View.VISIBLE
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
            mViewDataBinding?.toolBar?.imgToolBarRight?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check_circle))
            mViewDataBinding?.toolBar?.txtToolBarSelectSport?.text = "Unselect All"
            mViewDataBinding?.toolBar?.imgToolBarRight?.visibility = View.VISIBLE
        } else {
            mViewDataBinding?.toolBar?.imgToolBarRight?.visibility = View.INVISIBLE
            mViewDataBinding?.toolBar?.txtToolBarSelectSport?.text = "Select All"
            mViewDataBinding?.toolBar?.imgToolBarRight?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.radio_button_unchecked))
        }
    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "MY SPORTS"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_close)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.black))
            it.imgToolBarRight.visibility = View.INVISIBLE
            it.txtToolBarMiddleIcon.text = "Se"

            it.imgToolBarLeft.setOnClickListener {
                onBackPressed()
            }

            it.txtToolBarSelectSport.visibility = View.VISIBLE
            it.txtToolBarSelectSport.text = "Select All"
            it.txtToolBarSelectSport.textSize = 14f
            it.txtToolBarSelectSport.setTextColor(ContextCompat.getColor(this, R.color.mainColor))
            it.imgToolBarRight.setColorFilter(ContextCompat.getColor(this, R.color.mainColor))
            it.txtToolBarSelectSport.setOnClickListener {
                updateSelectedClick()
                adapter?.addArray(sports)
            }
        }
    }


}