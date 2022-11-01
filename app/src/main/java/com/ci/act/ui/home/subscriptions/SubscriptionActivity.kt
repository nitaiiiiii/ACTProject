package com.ci.act.ui.home.subscriptions

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySubscriptionBinding
import com.ci.act.ui.differentSports.adapter.DifferentSportsAdapter
import com.ci.act.ui.home.chargebee.ChargeBeeActivity
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.sendLog.SendLogActivity
import com.ci.act.ui.home.subscriptions.adapter.SubscriptionViewPagerAdapter
import com.ci.act.ui.home.subscriptions.model.SubscriptionModel

class SubscriptionActivity :
    BaseActivity<ActivitySubscriptionBinding, SubscriptionView, SubscriptionViewModel>(),
    SubscriptionView {

    private var subscriptionAdapter: SubscriptionViewPagerAdapter? = null

    override fun getContentView(): Int = R.layout.activity_subscription

    override fun setViewModelClass(): Class<SubscriptionViewModel> {
        return SubscriptionViewModel::class.java
    }

    override fun getNavigator(): SubscriptionView = this

    override fun getBindingVariable(): Int = BR.subscription

    override fun initViews(savedInstanceState: Bundle?) {
        fragmentView()
        adapterClick()
        setOnClickListener()

    }

    private fun setOnClickListener() {
        mViewDataBinding?.imgPersonalInfo?.setOnClickListener {
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fragmentView() {
        val subscriptionArray = ArrayList<SubscriptionModel>()
        subscriptionArray.add(SubscriptionModel("Premium"))
        subscriptionArray.add(SubscriptionModel("Platinum"))

        subscriptionAdapter = SubscriptionViewPagerAdapter()
        subscriptionAdapter?.addArrayList(subscriptionArray)
        mViewDataBinding?.viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mViewDataBinding?.viewPager?.adapter = subscriptionAdapter

        mViewDataBinding?.viewPager?.clipToPadding = false
        mViewDataBinding?.viewPager?.clipChildren = false
        mViewDataBinding?.viewPager?.offscreenPageLimit = 1
        mViewDataBinding?.viewPager?.setPadding(60, 0, 60, 30)
        mViewDataBinding?.viewPager?.getChildAt(0)?.overScrollMode = View.OVER_SCROLL_NEVER

        mViewDataBinding?.viewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mViewDataBinding?.pageIndicatorView?.setSelected(position)
            }
        })

    }

    private fun adapterClick() {
        subscriptionAdapter?.subscriptionClick(object :
            SubscriptionViewPagerAdapter.SubscriptionClick {
            override fun subscriptionClick(subscription: SubscriptionModel) {
                val intent = Intent(this@SubscriptionActivity, ChargeBeeActivity::class.java)
                startActivity(intent)
            }

        })
    }


}