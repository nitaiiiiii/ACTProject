package com.ci.act.ui.home.aboutUs

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityAboutUsBinding
import com.ci.act.ui.home.aboutUs.adapter.AboutUsViewPagerAdapter
import com.ci.act.ui.home.aboutUs.model.AboutUsModel
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.subscriptions.adapter.SubscriptionViewPagerAdapter

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding, AboutUsView, AboutUsViewModel>(),
    AboutUsView {

    private var aboutUsAdapter: AboutUsViewPagerAdapter? = null


    override fun getContentView(): Int = R.layout.activity_about_us

    override fun setViewModelClass(): Class<AboutUsViewModel> {
        return AboutUsViewModel::class.java
    }

    override fun getNavigator(): AboutUsView = this

    override fun getBindingVariable(): Int = BR.aboutUs

    override fun initViews(savedInstanceState: Bundle?) {
        setViewPager()
        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener() {


    }

    private fun setViewPager() {

        val listAboutUs = ArrayList<AboutUsModel>()

        listAboutUs.add(AboutUsModel(R.raw.animation))
        listAboutUs.add(AboutUsModel(R.raw.magic))
        listAboutUs.add(AboutUsModel(R.raw.animation))
        listAboutUs.add(AboutUsModel(R.raw.animation))
        listAboutUs.add(AboutUsModel(R.raw.animation))

        aboutUsAdapter = AboutUsViewPagerAdapter()
        aboutUsAdapter?.addArrayList(listAboutUs)
        mViewDataBinding?.viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mViewDataBinding?.viewPager?.adapter = aboutUsAdapter

        mViewDataBinding?.viewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mViewDataBinding?.indicator?.setSelected(position)
            }
        })

    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "ABOUT US"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_back_arrow)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.light_black))
            it.imgToolBarRight.visibility = View.INVISIBLE

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)
            }
        }
    }

}