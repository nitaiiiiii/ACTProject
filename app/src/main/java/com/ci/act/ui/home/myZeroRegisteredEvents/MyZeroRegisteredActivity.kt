package com.ci.act.ui.home.myZeroRegisteredEvents


import android.os.Bundle
import com.ci.act.R
import com.ci.act.BR
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMyZeroRegisteredBinding

class MyZeroRegisteredActivity:BaseActivity<ActivityMyZeroRegisteredBinding,MyZeroRegisteredView,MyZeroRegisteredViewModel>(),MyZeroRegisteredView{
    override fun getContentView(): Int = R.layout.activity_my_zero_registered

    override fun setViewModelClass(): Class<MyZeroRegisteredViewModel> {
        return MyZeroRegisteredViewModel::class.java
    }

    override fun getNavigator(): MyZeroRegisteredView = this

    override fun getBindingVariable(): Int = BR.myZeroRegisteredEvents

    override fun initViews(savedInstanceState: Bundle?) {

    }

}