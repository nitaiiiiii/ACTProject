package com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
    private val sharedSignatureViewModel : GuardianApprovalViewModel by activityViewModels()


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
        mViewDataBinding?.tvViewTerms?.setOnClickListener {
            if (check) {
                layoutTerms.visibility = View.GONE
                check = false
            } else {
                layoutTerms.visibility = View.VISIBLE
                check = true
            }

        }

        mViewDataBinding?.btnDigitalSign?.setOnClickListener {
            val signatureBoxFragment = SignatureBoxFragment()
            if (!isShowingDigitalSign) {
                isShowingDigitalSign = true
                activity?.supportFragmentManager?.let { it1 ->
                    signatureBoxFragment.show(
                        it1,
                        "Cancel Project"
                    )
                }
            }
        }
        mViewDataBinding?.btnUpload?.setOnClickListener {
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

    override fun addObservables() {
        sharedSignatureViewModel.getSignature().observe(viewLifecycleOwner, Observer {
            Log.e("check-signature", "check")
            if (it != null) {
                mViewDataBinding?.signature?.setImageBitmap(it.bitmap)
                mViewDataBinding?.signature?.visibility = View.VISIBLE

                mViewDataBinding?.btnDigitalSign!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icon_signature, 0)
                mViewDataBinding?.btnUpload!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_icon_upload, 0)

                if (it.isFrom == "S") {
                    mViewDataBinding?.btnDigitalSign?.setBackgroundResource(R.drawable.btn_round_background)
                    mViewDataBinding?.btnDigitalSign?.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                    mViewDataBinding?.btnDigitalSign!!.compoundDrawables[2].setTint(ContextCompat.getColor(requireContext(), R.color.textColor))
                    mViewDataBinding?.btnUpload?.setBackgroundResource(R.drawable.shape_btn_outline)
                    mViewDataBinding?.btnUpload?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    mViewDataBinding?.btnUpload!!.compoundDrawables[2].setTint(ContextCompat.getColor(requireContext(), R.color.black))
                } else  {
                    mViewDataBinding?.btnUpload?.setBackgroundResource(R.drawable.btn_round_background)
                    mViewDataBinding?.btnUpload?.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
                    mViewDataBinding?.btnUpload!!.compoundDrawables[2].setTint(ContextCompat.getColor(requireContext(), R.color.textColor))
                    mViewDataBinding?.btnDigitalSign?.setBackgroundResource(R.drawable.shape_btn_outline)
                    mViewDataBinding?.btnDigitalSign?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    mViewDataBinding?.btnDigitalSign!!.compoundDrawables[2].setTint(ContextCompat.getColor(requireContext(), R.color.black))
                }
            } else {
                mViewDataBinding?.signature?.visibility = View.GONE
            }
        })
    }


}