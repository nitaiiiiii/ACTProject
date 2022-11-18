package com.ci.act.ui.home.chargebee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityChargeBeeBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.subscriptions.SubscriptionActivity

class ChargeBeeActivity :
    BaseActivity<ActivityChargeBeeBinding, ChargeBeeView, ChargeBeeViewModel>(), ChargeBeeView {
    override fun getContentView(): Int = R.layout.activity_charge_bee

    override fun setViewModelClass(): Class<ChargeBeeViewModel> {
        return ChargeBeeViewModel::class.java
    }

    override fun getNavigator(): ChargeBeeView = this

    override fun getBindingVariable(): Int = BR.chargeBee

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.btnSignatureBox1?.setOnClickListener {
            val intent = Intent(this,EventsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let {
            it.imgToolBarRight.visibility = View.INVISIBLE
            it.txtToolbarHeading.text = "PAYMENT INSTRUCTIONS"
            it.txtToolbarHeading.textSize = 18F
            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this, SubscriptionActivity::class.java)
                startActivity(intent)
            }
        }
    }

}