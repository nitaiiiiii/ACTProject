package com.ci.act.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityOnBoardingBinding
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.ui.authentication.socialMedia.SocialMediaActivity
import com.ci.act.ui.onboarding.adapter.ViewPagerAdapter
import com.ci.act.ui.onboarding.pages.OnBoardingScreensFragment

class OnBoardingActivity :
    BaseActivity<ActivityOnBoardingBinding, OnBoardingView, OnBoardingViewModel>(), OnBoardingView {
    override fun setViewModelClass(): Class<OnBoardingViewModel> {
        return OnBoardingViewModel::class.java
    }


    override fun getContentView(): Int = R.layout.activity_on_boarding


    override fun getNavigator(): OnBoardingView = this

    override fun getBindingVariable(): Int = BR.onBoarding

    override fun onPause() {
        super.onPause()
        Log.v("ViewModel","OnBoardingActivity is onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.v("ViewModel","OnBoardingActivity is onStop")

    }

    override fun onRestart() {
        super.onRestart()
        Log.v("ViewModel","OnBoardingActivity is onRestart")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("ViewModel","OnBoardingActivity is onDestroy")

    }

    override fun initViews(savedInstanceState: Bundle?) {

        setOnClickListeners()
        Log.v("ViewModel","OnBoardingActivity is created")
        showOrHideStatusBar(false)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, addFragment())
        mViewDataBinding?.viewPagerOnBoardingScreen?.adapter = viewPagerAdapter
        mViewDataBinding?.viewPagerOnBoardingScreen?.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                showGetStarted(position == 2)
            }

        })
        mViewDataBinding?.pageIndicatorView?.setViewPager(mViewDataBinding?.viewPagerOnBoardingScreen)
        mViewDataBinding?.txtSkip?.setOnClickListener {
            mViewModel?.skipClick()
        }
    }

    private fun showGetStarted(show: Boolean) {
        mViewDataBinding?.groupSkipLayout?.visibility = if (show) View.GONE else View.VISIBLE
        mViewDataBinding?.btnGetStarted?.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun addFragment(): ArrayList<Fragment> {
        val introFragment = ArrayList<Fragment>()
        introFragment.add(OnBoardingScreensFragment())
        introFragment.add(OnBoardingScreensFragment())
        introFragment.add(OnBoardingScreensFragment())
        return introFragment
    }

    override fun skipClick(message: String) {


    }

    private fun setOnClickListeners(){
        mViewDataBinding?.btnGetStarted?.setOnClickListener {
            PreferenceHelper.getInstance().finishOnBoarding()
            val onBoard = Intent(this,SocialMediaActivity::class.java)
            startActivity(onBoard)
            finish()
        }

    }
}
