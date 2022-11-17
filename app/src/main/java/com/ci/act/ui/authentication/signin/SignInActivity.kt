package com.ci.act.ui.authentication.signin


import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySignInBinding
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.ui.authentication.forgotPassword.ForgotPasswordActivity
import com.ci.act.ui.authentication.pushNotification.PushNotificationActivity
import com.ci.act.ui.authentication.signup.SignUpActivity
import com.ci.act.ui.authentication.socialMedia.SocialMediaActivity
import com.ci.act.ui.customDialogFragments.cantAccessAccount.CantAccessAccountFragment
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.myZeroRegisteredEvents.MyZeroRegisteredActivity
import com.ci.act.util.showSnackBar


class SignInActivity : BaseActivity<ActivitySignInBinding, SignInView, SignInViewModel>(),
    SignInView {


    override fun getContentView(): Int = R.layout.activity_sign_in

    override fun setViewModelClass(): Class<SignInViewModel> {
        return SignInViewModel::class.java
    }

    companion object {
        var isShowingCancelProjectDialog = false
    }

    override fun getNavigator(): SignInView = this

    override fun getBindingVariable(): Int = BR.signIn

    override fun initViews(savedInstanceState: Bundle?) {
        spannableSignInStrings()
        setOnClickListener()
        validateFields()
    }

    private fun spannableSignInStrings() {
        val text = "Sign Up"

        val spannableString = SpannableString(text).apply {
            setSpan(UnderlineSpan(), 0, text.length, 0)
        }
        mViewDataBinding?.txtSignInEmailSignUpScreen?.text = spannableString

    }

    private fun setOnClickListener() {
        mViewDataBinding?.txtForgetPasswordHeading?.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        mViewDataBinding?.txtSignInEmailSignUpScreen?.setOnClickListener {
            val action = Intent(this, SignUpActivity::class.java)
            startActivity(action)
        }
        mViewDataBinding?.imgBackButton?.setOnClickListener {
            val action = Intent(this, SocialMediaActivity::class.java)
            startActivity(action)
        }

        mViewDataBinding?.txtSocialMediaCantAccessScreen?.setOnClickListener {
            val cantAccessFragment = CantAccessAccountFragment()
            if (!isShowingCancelProjectDialog) {
                isShowingCancelProjectDialog = true
                supportFragmentManager?.let { it1 ->
                    cantAccessFragment.show(
                        it1,
                        "Cancel Project"
                    )
                }
            }
        }

    }

    private fun validateFields() {
        mViewDataBinding?.btnSignIn?.setOnClickListener {
            if (mViewDataBinding?.editText?.text?.isEmpty() == true) {
                showErrorMessage(
                    "Email Address is Required..."
                )
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    mViewDataBinding?.editText?.text?.trim().toString()
                ).matches()
            ) {
                mViewDataBinding?.editText?.requestFocus()
                showErrorMessage(
                    "Not a Valid Email Address..."
                )
            } else if (mViewDataBinding?.editTextPassword?.text?.isEmpty() == true) {
                showErrorMessage(
                    "password is required..."
                )
            } else if (mViewDataBinding?.editTextPassword?.text?.length!! < 8 || mViewDataBinding?.editTextPassword?.text?.length!! >= 16) {
                mViewDataBinding?.editTextPassword?.requestFocus()
                showErrorMessage(
                    "Password must be 8 to 16 characters..."
                )

            } else {
                showSuccessMessage("success...")
                PreferenceHelper.getInstance().setUserDetails(mViewDataBinding?.editText?.text?.trim().toString())
                if(PreferenceHelper.getInstance().isPushNotificationFinished()) {
                    val onBoard = Intent(this, MyZeroRegisteredActivity::class.java)
                    startActivity(onBoard)
                    finish()
                } else {
                    val onBoard1 = Intent(this,PushNotificationActivity::class.java)
                    startActivity(onBoard1)
                    finish()

                }
            }
        }
    }


}