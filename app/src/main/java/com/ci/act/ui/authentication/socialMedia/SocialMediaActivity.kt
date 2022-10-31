package com.ci.act.ui.authentication.socialMedia

import android.content.Intent
import android.os.Bundle
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
        spannableStrings()
        setOnClickListener()
    }

    private fun spannableStrings() {
        val text = "Sign Up"

        val spannableString = SpannableString(text).apply {
            setSpan(UnderlineSpan(), 0, text.length, 0)
        }
        mViewDataBinding?.txtSocialMediaEmailSignUpScreen?.text = spannableString

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

}