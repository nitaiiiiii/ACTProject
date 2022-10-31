package com.ci.act.ui.home.chargebee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityChargeBeeBinding
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
    }

    private fun setOnClickListener() {
        mViewDataBinding?.imgPaymentInstructions?.setOnClickListener {
            val intent = Intent(this, SubscriptionActivity::class.java)
            startActivity(intent)
        }
    }

}