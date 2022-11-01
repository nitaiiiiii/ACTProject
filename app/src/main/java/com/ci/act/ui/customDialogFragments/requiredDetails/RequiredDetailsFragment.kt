package com.ci.act.ui.customDialogFragments.requiredDetails

import android.content.Intent
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentRequiredDetailsBinding
import com.ci.act.ui.authentication.forgotPassword.ForgotPasswordActivity
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.authentication.signup.SignUpActivity

class RequiredDetailsFragment :
    BaseDialogFragmentNew<FragmentRequiredDetailsBinding, RequiredDetailsView, RequiredDetailsViewModel>(),
    RequiredDetailsView {
    override fun getContentView(): Int = R.layout.fragment_required_details

    override fun setViewModelClass(): Class<RequiredDetailsViewModel> {
        return RequiredDetailsViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.requiredDetailsFragment

    override fun getNavigator(): RequiredDetailsView = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.MyDialogPopup)

        isCancelable = false
    }

    override fun initViews(savedInstanceState: Bundle?) {
        mViewDataBinding?.txtRequiredDetailsAccess?.setOnClickListener {
            ForgotPasswordActivity.isRequiredDetails = false
            dialog?.dismiss()
        }
        mViewDataBinding?.btnRequiredLocation?.setOnClickListener {
            val intent = Intent(requireActivity(),SignUpActivity::class.java)
            startActivity(intent)
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