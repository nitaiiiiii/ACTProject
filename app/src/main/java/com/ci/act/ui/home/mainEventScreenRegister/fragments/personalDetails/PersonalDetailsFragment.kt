package com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentPersonalDetailsBinding
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.homeDetails.HomeDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.SchoolDetailsFragment
import com.ci.act.util.showSnackBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main_event_screen_register.*
import kotlinx.android.synthetic.main.fragment_personal_details.*
import kotlinx.coroutines.selects.select

class PersonalDetailsFragment :
    BaseDialogFragmentNew<FragmentPersonalDetailsBinding, PersonalDetailsView, PersonalDetailsViewModel>(),
    PersonalDetailsView {

    private lateinit var communicator: Communicator
    var isSelected: Boolean = true


    override fun getContentView(): Int = R.layout.fragment_personal_details

    override fun setViewModelClass(): Class<PersonalDetailsViewModel> {
        return PersonalDetailsViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.personalDetails

    override fun getNavigator(): PersonalDetailsView = this

    override fun initViews(savedInstanceState: Bundle?) {

        communicator = activity as Communicator
        setOnClickListener()
        validatePersonalDetails()



    }

    private fun setOnClickListener() {

    }

    private fun validatePersonalDetails() {
        mViewDataBinding?.btnSignature?.setOnClickListener {
            if (mViewDataBinding?.editTextFirstName?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextFirstName?.requestFocus()
                showSnackBar(requireView(), "First name is required...", false)
            } else if (mViewDataBinding?.editTextLastName?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextLastName?.requestFocus()
                showSnackBar(requireView(), "Last name is required...", false)

            } else if (mViewDataBinding?.editTextEmailAddress?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "Email address is required...", false)

            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    mViewDataBinding?.editTextEmailAddress?.text?.trim().toString()
                ).matches()
            ) {
                showSnackBar(requireView(), "Not a valid email address...", false)
                mViewDataBinding?.editTextEmailAddress?.requestFocus()
            } else if (mViewDataBinding?.editTextMobileNumber?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextMobileNumber?.requestFocus()
                showSnackBar(requireView(), "Mobile Number is required...", false)
            } else if (mViewDataBinding?.editTextMobileNumber?.text?.filter { number -> number.isDigit() }
                    .toString().length != 10) {
                mViewDataBinding?.editTextMobileNumber?.requestFocus()
                showSnackBar(requireView(), "Not a valid mobile number...", false)

            } else if (mViewDataBinding?.editTextPosition?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextPosition?.requestFocus()
                showSnackBar(requireView(), "Position is required...", false)

            } else if (mViewDataBinding?.editTextInsuranceCompany?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextInsuranceCompany?.requestFocus()
                showSnackBar(requireView(), "Insurance company is required...", false)

            } else if (mViewDataBinding?.editTextPolicyNumber?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextPolicyNumber?.requestFocus()
                showSnackBar(requireView(), "Policy number is required...", false)

            } else {
                val schoolDetails = SchoolDetailsFragment()
                communicator.loadFragments(schoolDetails, true, 2)
            }
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