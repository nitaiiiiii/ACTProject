package com.ci.act.ui.onboarding.pages


import android.os.Bundle
import com.bumptech.glide.Glide
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentOnBoardingScreensBinding
import com.ci.act.ui.onboarding.adapter.ViewPagerAdapter
import com.ci.act.ui.onboarding.pages.model.OnBoardingModel


class OnBoardingScreensFragment :
    BaseFragment<FragmentOnBoardingScreensBinding, OnBoardingScreenView, OnBoardingScreensViewModel>(),
    OnBoardingScreenView {

    private val screenData = ArrayList<OnBoardingModel>()

    override fun getContentView(): Int = R.layout.fragment_on_boarding_screens

    override fun setViewModel(): OnBoardingScreensViewModel? = OnBoardingScreensViewModel()

    override fun getBindingVariable(): Int = BR.onBoarding

    override fun getNavigator(): OnBoardingScreenView = this

    override fun initViews(savedInstanceState: Bundle?) {
        addData()
        val position: Int = arguments?.getInt(ViewPagerAdapter.BUNDLE_KEY_POSITION) ?: 0
        val currentData = screenData[position]
        Glide.with(mViewDataBinding?.imgOnBoardScreenImage?.context!!).load(currentData.image)
            .into(mViewDataBinding?.imgOnBoardScreenImage!!)
    }

    private fun addData() {
        screenData.add(
            OnBoardingModel(
                "Base Architecture",
                "One",
                "Description one",
                R.drawable.onboarding1
            )
        )
        screenData.add(
            OnBoardingModel(
                "Base Architecture",
                "Two",
                "Description Two",
                R.drawable.onboarding2
            )
        )
        screenData.add(
            OnBoardingModel(
                "Base Architecture",
                "Three",
                "Description Three",
                R.drawable.onboarding3
            )
        )
    }


}
