package com.ci.act.ui.customDialogFragments.signOut

import android.content.Intent
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentSignOutBinding
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.ui.authentication.socialMedia.SocialMediaActivity
import com.ci.act.ui.editProfile.EditProfileActivity
import com.ci.act.ui.home.events.EventsActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SignOutFragment :
    BaseDialogFragmentNew<FragmentSignOutBinding, SignOutView, SignOutViewModel>(), SignOutView {
    override fun getContentView(): Int = R.layout.fragment_sign_out

    override fun setViewModelClass(): Class<SignOutViewModel> {
        return SignOutViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.signOut

    override fun getNavigator(): SignOutView = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.MyDialogPopup)

        isCancelable = false
    }

    override fun initViews(savedInstanceState: Bundle?) {
        mViewDataBinding?.txtSignatureBoxClose?.setOnClickListener {
            EventsActivity.isShowingSignOut = false
            dialog?.dismiss()
        }

        mViewDataBinding?.btnSignatureBox?.setOnClickListener {
            PreferenceHelper.getInstance().clearUserDetails()
            val auth = FirebaseAuth.getInstance()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
            if (auth.currentUser != null) {
                auth.signOut()
                mGoogleSignInClient.signOut()
            }
            EventsActivity.isShowingSignOut = false
            dialog?.dismiss()
            startActivity(Intent(activity, SocialMediaActivity::class.java))
            requireActivity().finishAffinity()
        }
    }

    override fun addObservables() {
    }

    override fun showApiErrorMessage(message: String?) {
    }

    override fun noInternetError() {
    }

    override fun onUnknownError(error: String?) {
    }

    override fun onTimeout() {
    }

    override fun onNetworkError() {
    }

    override fun showApiDialog(message: String) {
    }

    override fun showSuccessMessage(message: String?) {
    }

    override fun showErrorMessage(message: String?) {
    }

    override fun showErrorMessage(icon: Int?, heading: String?, desc: String?, btnName: String?) {
    }

    override fun onApiFailed(message: String?) {
    }

    override fun onForceLogOut(message: String?) {
    }
}