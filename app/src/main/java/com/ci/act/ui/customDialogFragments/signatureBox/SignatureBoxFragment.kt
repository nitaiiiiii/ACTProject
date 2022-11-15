package com.ci.act.ui.customDialogFragments.signatureBox

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentSignatureBoxBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.home.mainEventScreenRegister.MainEventScreenRegisterActivity
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalView
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalViewModel

class SignatureBoxFragment :
    BaseDialogFragmentNew<FragmentSignatureBoxBinding, SignatureBoxView, SignatureBoxViewModel>(),
    SignatureBoxView {

    private val sharedSignatureViewModel : GuardianApprovalViewModel by activityViewModels()

    override fun getContentView(): Int = R.layout.fragment_signature_box

    override fun setViewModelClass(): Class<SignatureBoxViewModel> {
        return SignatureBoxViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.signatureBox

    override fun getNavigator(): SignatureBoxView = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.MyDialogPopup)

        isCancelable = false
    }

    override fun initViews(savedInstanceState: Bundle?) {
        mViewDataBinding?.txtSignatureBoxClose?.setOnClickListener {
            GuardianApprovalFragment.isShowingDigitalSign = false
            dialog?.dismiss()
        }

        mViewDataBinding?.btnSignatureBox?.setOnClickListener{
            if (mViewDataBinding?.edtAccessAccountSignatureMultiLine?.isBitmapEmpty == false){
                val bitmap: Bitmap = mViewDataBinding?.edtAccessAccountSignatureMultiLine?.signatureBitmap!!
                sharedSignatureViewModel.setSignature("S", bitmap)
                GuardianApprovalFragment.isShowingDigitalSign = false
                dialog?.dismiss()
            } else {
                GuardianApprovalFragment.isShowingDigitalSign = false
                dialog?.dismiss()
            }
        }

        mViewDataBinding?.imgSignature?.setOnClickListener{
            mViewDataBinding?.edtAccessAccountSignatureMultiLine?.clearCanvas()
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