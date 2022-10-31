package com.ci.act.ui.changePassword

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity: BaseActivity<ActivityChangePasswordBinding,ChangePasswordView,ChangePasswordViewModel>(),ChangePasswordView {
    override fun getContentView(): Int = R.layout.activity_change_password

    override fun setViewModelClass(): Class<ChangePasswordViewModel> {
        return ChangePasswordViewModel::class.java
    }

    override fun getNavigator(): ChangePasswordView = this

    override fun getBindingVariable(): Int = BR.changePassword

    override fun initViews(savedInstanceState: Bundle?) {
    }

}