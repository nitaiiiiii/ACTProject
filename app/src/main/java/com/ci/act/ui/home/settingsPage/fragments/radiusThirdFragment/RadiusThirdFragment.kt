package com.ci.act.ui.home.settingsPage.fragments.radiusThirdFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentRadiusThirdBinding

class RadiusThirdFragment :
    BaseFragment<FragmentRadiusThirdBinding, RadiusThirdView, RadiusThirdViewModel>(),RadiusThirdView {
    override fun getContentView(): Int = R.layout.fragment_radius_third

    override fun setViewModel(): RadiusThirdViewModel? = RadiusThirdViewModel()

    override fun getBindingVariable(): Int = BR.radiusThirdFragment

    override fun getNavigator(): RadiusThirdView = this

    override fun initViews(savedInstanceState: Bundle?) {
    }

}