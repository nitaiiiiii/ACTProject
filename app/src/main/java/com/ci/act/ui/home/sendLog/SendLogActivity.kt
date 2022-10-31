package com.ci.act.ui.home.sendLog

import android.content.Intent
import android.os.Bundle
import com.ci.act.R
import com.ci.act.BR
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySendLogBinding
import com.ci.act.ui.home.events.EventsActivity

class SendLogActivity:BaseActivity<ActivitySendLogBinding,SendLogView,SendLogViewModel>(),SendLogView{
    override fun getContentView(): Int = R.layout.activity_send_log

    override fun setViewModelClass(): Class<SendLogViewModel> {
        return SendLogViewModel::class.java
    }

    override fun getNavigator(): SendLogView = this

    override fun getBindingVariable(): Int = BR.sendLog

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
    }
    private fun setOnClickListener() {
        mViewDataBinding?.imgContactUs?.setOnClickListener{
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

    }
}