package com.ci.act.ui.authentication.socialMedia

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySocialMediaBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.authentication.signup.SignUpActivity

class SocialMediaActivity :
    BaseActivity<ActivitySocialMediaBinding, SocialMediaView, SocialMediaViewModel>(),
    SocialMediaView {
    override fun getContentView(): Int = R.layout.activity_social_media

    override fun setViewModelClass(): Class<SocialMediaViewModel> {
        return SocialMediaViewModel::class.java

    }

    override fun getNavigator(): SocialMediaView = this

    override fun getBindingVariable(): Int = BR.socialMedia

    override fun initViews(savedInstanceState: Bundle?) {
        spannableText()
        setOnClickListener()
    }
    private fun setOnClickListener() {
        mViewDataBinding?.txtSocialMediaEmailLoginScreen?.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        mViewDataBinding?.txtSocialMediaEmailSignUpScreen?.setOnClickListener {
            val action = Intent(this, SignUpActivity::class.java)
            startActivity(action)
            finish()
        }

    }

    private fun spannableText() {

        //these for Terms
        val spannableString = SpannableString("By Signing in, you agree to our Terms & Privacy")
        val underlineSpan = UnderlineSpan()
        spannableString.setSpan(underlineSpan, 32, 37, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        mViewDataBinding?.txtSocialMediaScreen?.setText(spannableString)


        //these for Privacy for
        val underlineSpan2 = UnderlineSpan()
        spannableString.setSpan(underlineSpan2, 40, 47, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        mViewDataBinding?.txtSocialMediaScreen?.setText(spannableString)


        //for signUp
        //txtSocialMediaEmailSignUpScreen
        val signUp = SpannableString("Sign Up")
        val underlineSpan3 = UnderlineSpan()
        signUp.setSpan(underlineSpan3, 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        mViewDataBinding?.txtSocialMediaEmailSignUpScreen?.setText(signUp)


    }

}