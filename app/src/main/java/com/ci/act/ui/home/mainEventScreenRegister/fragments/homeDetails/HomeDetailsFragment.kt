package com.ci.act.ui.home.mainEventScreenRegister.fragments.homeDetails


import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentHomeDetailsBinding
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.PersonalDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.SchoolDetailsFragment
import com.ci.act.util.showSnackBar

class HomeDetailsFragment :
    BaseDialogFragmentNew<FragmentHomeDetailsBinding, HomeDetailsView, HomeDetailsViewModel>(),
    HomeDetailsView {
    private lateinit var communicator: Communicator

    override fun getContentView(): Int = R.layout.fragment_home_details

    override fun setViewModelClass(): Class<HomeDetailsViewModel> {
        return HomeDetailsViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.homeDetails

    override fun getNavigator(): HomeDetailsView = this

    override fun initViews(savedInstanceState: Bundle?) {
        communicator = activity as Communicator
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.btnSignatureBox?.setOnClickListener {
            validatePersonalDetails()
        }
    }

    private fun validatePersonalDetails() {
        mViewDataBinding?.btnSignatureBox?.setOnClickListener {
            if (mViewDataBinding?.editTextFirstName?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextFirstName?.requestFocus()
                showSnackBar(requireView(), "School name is required...", false)
            }  else if (mViewDataBinding?.editTextState?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "State is required...", false)

            } else if (mViewDataBinding?.editTextCity?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "City is required...", false)

            } else if (mViewDataBinding?.editTextCode?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "Zip Code is required...", false)

            }  else if (mViewDataBinding?.editTextCoder?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "Home Phone is required...", false)

            }  else {
                val guardianApprovalFragment = GuardianApprovalFragment()
                communicator.loadFragments(guardianApprovalFragment, true, 4)            }
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