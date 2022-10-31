package com.ci.act.ui.customDialogFragments.cantAccessAccount

import android.content.Intent
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentCantAccessAccountBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.authentication.signup.SignUpActivity

class CantAccessAccountFragment: BaseDialogFragmentNew<FragmentCantAccessAccountBinding,CantAccessAccountView,CantAccessAccountViewModel>(),CantAccessAccountView{
    override fun getContentView(): Int = R.layout.fragment_cant_access_account

    override fun setViewModelClass(): Class<CantAccessAccountViewModel> {
        return CantAccessAccountViewModel::class.java
    }

    override fun getBindingVariable(): Int= BR.cantAccessFragment

    override fun getNavigator(): CantAccessAccountView = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.MyDialogPopup)

        isCancelable = false
    }

    override fun initViews(savedInstanceState: Bundle?) {
        mViewDataBinding?.txtCantAccountAccess?.setOnClickListener {
            SignInActivity.isShowingCancelProjectDialog = false
            dialog?.dismiss()
        }
        mViewDataBinding?.btnAccessAccount?.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        mViewDataBinding?.btnAccessAccount?.setOnClickListener {
            if (mViewDataBinding?.editTextName?.text?.isEmpty() == true) {
                showErrorMessage(
                    "Email Address is Required..."
                )
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    mViewDataBinding?.editTextEmail?.text?.trim().toString()
                ).matches()
            ) {
                mViewDataBinding?.editTextEmail?.requestFocus()
                showErrorMessage(
                    "Not a Valid Email Address..."
                )
            } else if (mViewDataBinding?.editTextMobile?.text?.isEmpty() == true) {
                showErrorMessage(
                    "password is required..."
                )
            } else if (mViewDataBinding?.editTextMobile?.text?.length!! <= 10) {
                mViewDataBinding?.editTextMobile?.requestFocus()
                showErrorMessage(
                    "Mobile Number is number..."
                )

            } else {
                val intent = Intent(requireContext(), SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }


    override fun addObservables() {}
    override fun showApiErrorMessage(message: String?) {}
    override fun noInternetError() {}
    override fun onUnknownError(error: String?) {}
    override fun onTimeout() {}
    override fun onNetworkError() {}
    override fun showApiDialog(message: String) {}
    override fun showSuccessMessage(message: String?) {}
    override fun showErrorMessage(message: String?) {}
    override fun showErrorMessage(icon: Int?, heading: String?, desc: String?, btnName: String?) {}
    override fun onApiFailed(message: String?) {}
    override fun onForceLogOut(message: String?) {}


}