package com.ci.act.ui.home.contactUs

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityContactUsBinding
import com.ci.act.ui.home.events.EventsActivity

class ContactUsActivity :
    BaseActivity<ActivityContactUsBinding, ContactUsView, ContactUsViewModel>(), ContactUsView {
    override fun getContentView(): Int = R.layout.activity_contact_us

    override fun setViewModelClass(): Class<ContactUsViewModel> {
        return ContactUsViewModel::class.java
    }

    override fun getNavigator(): ContactUsView = this

    override fun getBindingVariable(): Int = BR.contactUs

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener() {

    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "CONTACT US"
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