package com.ci.act.ui.customDialogFragments.signOut

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentSignOutBinding
import com.ci.act.ui.editProfile.EditProfileActivity
import com.ci.act.ui.home.events.EventsActivity

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