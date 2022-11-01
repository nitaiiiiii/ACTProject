package com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentGuardianApprovalBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.customDialogFragments.cantAccessAccount.CantAccessAccountFragment
import com.ci.act.ui.customDialogFragments.signatureBox.SignatureBoxFragment
import com.ci.act.ui.customDialogFragments.uploadSignature.UploadSignatureFragment
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.MainEventScreenRegisterActivity
import kotlinx.android.synthetic.main.fragment_guardian_approval.*
import kotlinx.android.synthetic.main.inflate_faqs_list.*

class GuardianApprovalFragment :
    BaseFragment<FragmentGuardianApprovalBinding, GuardianApprovalView, GuardianApprovalViewModel>(),
    GuardianApprovalView {
    private lateinit var communicator: Communicator
    var check: Boolean = false

    companion object {
        var isShowingDigitalSign = false
        var isUploadingDigitalSign = false
    }


    override fun getContentView(): Int = R.layout.fragment_guardian_approval

    override fun setViewModel(): GuardianApprovalViewModel = GuardianApprovalViewModel()

    override fun getBindingVariable(): Int = BR.guardianApproval

    override fun getNavigator(): GuardianApprovalView = this

    override fun initViews(savedInstanceState: Bundle?) {
        communicator = activity as Communicator
        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        mViewDataBinding?.txtPaymentQuestionsInformation?.setOnClickListener {
            if (check) {
                txtInformationAnswers1.visibility = View.GONE
                check = false
            } else {
                txtInformationAnswers1.visibility = View.VISIBLE
                check = true
            }

        }

        mViewDataBinding?.txtDigitalSign?.setOnClickListener {
            val signatureBoxFragment = SignatureBoxFragment()
            if (!GuardianApprovalFragment.isShowingDigitalSign) {
                GuardianApprovalFragment.isShowingDigitalSign = true
                activity?.supportFragmentManager?.let { it1 ->
                    signatureBoxFragment.show(
                        it1,
                        "Cancel Project"
                    )
                }
            }
        }
        mViewDataBinding?.txtDigitalSignUpload?.setOnClickListener {
            val uploadSignatureFragment = UploadSignatureFragment()
            if (!GuardianApprovalFragment.isUploadingDigitalSign) {
                GuardianApprovalFragment.isUploadingDigitalSign = true
                activity?.supportFragmentManager?.let { it1 ->
                    uploadSignatureFragment.show(
                        it1,
                        "Cancel Project"
                    )
                }
            }
        }

    }

}