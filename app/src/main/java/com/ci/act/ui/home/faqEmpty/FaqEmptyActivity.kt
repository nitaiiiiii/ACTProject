package com.ci.act.ui.home.faqEmpty

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityFaqEmptyBinding
import com.ci.act.ui.home.contactUs.ContactUsActivity
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.faqList.FaqListActivity


class FaqEmptyActivity : BaseActivity<ActivityFaqEmptyBinding, FaqEmptyView, FaqEmptyViewModel>(),
    FaqEmptyView {
    override fun getContentView(): Int = R.layout.activity_faq_empty

    override fun setViewModelClass(): Class<FaqEmptyViewModel> {
        return FaqEmptyViewModel::class.java
    }

    override fun getNavigator(): FaqEmptyView = this

    override fun getBindingVariable(): Int = BR.faqEmpty

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener() {

        mViewDataBinding?.btnEmptyRelevantQuestions?.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }
        mViewDataBinding?.imgPlayingFaqEmpty?.setOnClickListener {
            val intent = Intent(this, FaqListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "FAQ's"
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