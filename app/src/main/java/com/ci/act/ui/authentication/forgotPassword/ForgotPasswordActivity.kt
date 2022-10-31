package com.ci.act.ui.authentication.forgotPassword

import android.content.Intent
import android.os.Bundle
import com.ci.act.R
import com.ci.act.BR
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityForgotPasswordBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.authentication.signup.SignUpActivity

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding,ForgotPasswordView,ForgotPasswordViewModel>(), ForgotPasswordView{
    override fun getContentView(): Int = R.layout.activity_forgot_password

    override fun setViewModelClass(): Class<ForgotPasswordViewModel> {
       return ForgotPasswordViewModel::class.java
    }

    override fun getNavigator(): ForgotPasswordView = this

    override fun getBindingVariable(): Int = BR.forgotPassword

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
        validateFields()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.imgBackButton?.setOnClickListener {
            val action = Intent(this, SignInActivity::class.java)
            startActivity(action)
            finish()
        }
    }

    private fun validateFields(){
        mViewDataBinding?.btnSignIn?.setOnClickListener {
            if (mViewDataBinding?.editTextForgotPassword?.text?.isEmpty() == true) {
                showErrorMessage(
                    "Email Address is Required..."
                )
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    mViewDataBinding?.editTextForgotPassword?.text?.trim().toString()
                ).matches()
            ) {
                mViewDataBinding?.editTextForgotPassword?.requestFocus()
                showErrorMessage(
                    "Not a Valid Email Address..."
                )
            } else {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }


}