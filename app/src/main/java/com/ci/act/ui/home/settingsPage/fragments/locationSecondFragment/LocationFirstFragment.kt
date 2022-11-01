package com.ci.act.ui.home.settingsPage.fragments.locationSecondFragment

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentLocationFirstBinding

class LocationFirstFragment :
    BaseFragment<FragmentLocationFirstBinding, LocationFirstView, LocationFirstViewModel>(),
    LocationFirstView {
    override fun getContentView(): Int = R.layout.fragment_location_first

    override fun setViewModel(): LocationFirstViewModel? = LocationFirstViewModel()

    override fun getBindingVariable(): Int = BR.locationFirstFragment

    override fun getNavigator(): LocationFirstView = this

    override fun initViews(savedInstanceState: Bundle?) {
    }

}