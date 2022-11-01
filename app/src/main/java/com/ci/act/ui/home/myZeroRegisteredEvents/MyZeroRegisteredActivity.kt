package com.ci.act.ui.home.myZeroRegisteredEvents


import android.content.Intent
import android.os.Bundle
import com.ci.act.R
import com.ci.act.BR
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMyZeroRegisteredBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.upcomingEvents.UpcomingEventsActivity

class MyZeroRegisteredActivity:BaseActivity<ActivityMyZeroRegisteredBinding,MyZeroRegisteredView,MyZeroRegisteredViewModel>(),MyZeroRegisteredView{
    override fun getContentView(): Int = R.layout.activity_my_zero_registered

    override fun setViewModelClass(): Class<MyZeroRegisteredViewModel> {
        return MyZeroRegisteredViewModel::class.java
    }

    override fun getNavigator(): MyZeroRegisteredView = this

    override fun getBindingVariable(): Int = BR.myZeroRegisteredEvents

    override fun initViews(savedInstanceState: Bundle?) {
          setOnClickListener()
    }

    private fun setOnClickListener(){

        mViewDataBinding?.imgZeroRegisteredPlaying?.setOnClickListener{
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

        mViewDataBinding?.btnZeroRegistered?.setOnClickListener{
            val intent  = Intent(this,UpcomingEventsActivity::class.java)
            startActivity(intent)
        }
    }

}