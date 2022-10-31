package com.ci.act.ui.invoice

import android.content.Intent
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityInvoicesBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.listInvoices.ListInvoicesActivity

class InvoicesActivity:BaseActivity<ActivityInvoicesBinding,InvoicesView,InvoicesViewModel>(),InvoicesView{
    override fun getContentView(): Int = R.layout.activity_invoices

    override fun setViewModelClass(): Class<InvoicesViewModel> {
        return InvoicesViewModel::class.java
    }

    override fun getNavigator(): InvoicesView = this

    override fun getBindingVariable(): Int = BR.invoices

    override fun initViews(savedInstanceState: Bundle?) {

        setOnClickListener()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.imgInvoices?.setOnClickListener {
            val intent = Intent(this,EventsActivity::class.java)
            startActivity(intent)
        }
        mViewDataBinding?.btnInvoices?.setOnClickListener {
            val intent = Intent(this,ListInvoicesActivity::class.java)
            startActivity(intent)
        }

    }


}