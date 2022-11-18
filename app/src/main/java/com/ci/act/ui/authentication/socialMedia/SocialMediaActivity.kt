package com.ci.act.ui.authentication.socialMedia

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySocialMediaBinding
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.ui.authentication.pushNotification.PushNotificationActivity
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.authentication.signup.SignUpActivity
import com.ci.act.ui.home.myZeroRegisteredEvents.MyZeroRegisteredActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SocialMediaActivity :
    BaseActivity<ActivitySocialMediaBinding, SocialMediaView, SocialMediaViewModel>(),
    SocialMediaView {

    companion object {
        private const val RC_GOOGLE_SIGN_IN = 120
    }
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun getContentView(): Int = R.layout.activity_social_media

    override fun setViewModelClass(): Class<SocialMediaViewModel> {
        return SocialMediaViewModel::class.java

    }

    override fun getNavigator(): SocialMediaView = this

    override fun getBindingVariable(): Int = BR.socialMedia

    override fun initViews(savedInstanceState: Bundle?) {
        spannableText()
        firebaseSetUp()
        setOnClickListener()
    }

    private fun googleSignIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    private fun firebaseSetUp() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        mViewDataBinding?.signInProgress?.visibility = View.VISIBLE
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)
                    Log.e("google", "$account")
                    Log.e("google", "${account.id}")
                    Log.e("google", "${account.idToken}")

                    firebaseAuthWihGoogle(account.idToken)
                } catch (e: ApiException) {
//                    mViewDataBinding?.signInProgress?.visibility = View.GONE
                    Log.e("google", "login failed")
                }
            } else {
//                mViewDataBinding?.signInProgress?.visibility = View.GONE
                Log.e("google", exception.toString())
            }
        }
    }

    private fun firebaseAuthWihGoogle(id: String?) {
        val credentials = GoogleAuthProvider.getCredential(id, null)
        mAuth.signInWithCredential(credentials)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//                    mViewDataBinding?.signInProgress?.visibility = View.GONE
                    val user = mAuth.currentUser
                    if (PreferenceHelper.getInstance().isPushNotificationFinished()) {
                        PreferenceHelper.getInstance().setUserDetails(user?.displayName.toString())
                        startActivity(Intent(applicationContext, MyZeroRegisteredActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(applicationContext, PushNotificationActivity::class.java))
                        finish()
                    }
                    Log.e("google - userdetails", "${user?.email}")
                } else {
//                    mViewDataBinding?.signInProgress?.visibility = View.GONE
                    Log.e("google - userdetails", "${task.exception.toString()}")
                }
            }
    }


    private fun setOnClickListener() {
        mViewDataBinding?.txtSocialMediaEmailLoginScreen?.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        mViewDataBinding?.txtSocialMediaEmailSignUpScreen?.setOnClickListener {
            val action = Intent(this, SignUpActivity::class.java)
            startActivity(action)
            finishAffinity()
        }

        mViewDataBinding?.imgGoogleSocialMedia?.setOnClickListener {
            googleSignIn()
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