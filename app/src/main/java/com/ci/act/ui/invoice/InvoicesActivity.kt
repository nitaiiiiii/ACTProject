package com.ci.act.ui.invoice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityInvoicesBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.listInvoices.ListInvoicesActivity

class InvoicesActivity : BaseActivity<ActivityInvoicesBinding, InvoicesView, InvoicesViewModel>(),
    InvoicesView {
    override fun getContentView(): Int = R.layout.activity_invoices

    override fun setViewModelClass(): Class<InvoicesViewModel> {
        return InvoicesViewModel::class.java
    }

    override fun getNavigator(): InvoicesView = this

    override fun getBindingVariable(): Int = BR.invoices

    override fun initViews(savedInstanceState: Bundle?) {

        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener() {

        mViewDataBinding?.btnInvoices?.setOnClickListener {
            val intent = Intent(this, ListInvoicesActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "INVOICES"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_back_arrow)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.light_black))
            it.imgToolBarRight.visibility = View.INVISIBLE

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)            }
        }
    }


}