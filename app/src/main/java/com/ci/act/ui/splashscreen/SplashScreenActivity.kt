package com.ci.act.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySplashScreenBinding
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.ui.authentication.socialMedia.SocialMediaActivity
import com.ci.act.ui.differentSports.DifferentSportsActivity
import com.ci.act.ui.onboarding.OnBoardingActivity

class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding, SplashScreenView, SplashScreenViewModel>(),
    SplashScreenView {
    override fun setViewModelClass(): Class<SplashScreenViewModel> {
        return SplashScreenViewModel::class.java
    }

    override fun getContentView(): Int = R.layout.activity_splash_screen


    override fun getNavigator(): SplashScreenView = this

    override fun getBindingVariable(): Int = BR.splashScreen

    override fun initViews(savedInstanceState: Bundle?) {
        showOrHideStatusBar(false)
        changeScreenTo(OnBoardingActivity::class.java, 5000)

        mViewDataBinding?.imgLoading?.let {
            Glide.with(this)
                .load(R.drawable.loading2)
                .into(it)
        }
    }

    private fun changeScreenTo(mActivity: Class<*>, runningTime: Long) {
        Handler(Looper.myLooper()!!).postDelayed({
            if (PreferenceHelper.getInstance().isOnBoardingFinished() == true) {
                val intent = Intent(this, SocialMediaActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val action = Intent(this, DifferentSportsActivity::class.java)
                startActivity(action)
                finish()
            }

        }, runningTime)
    }

}
