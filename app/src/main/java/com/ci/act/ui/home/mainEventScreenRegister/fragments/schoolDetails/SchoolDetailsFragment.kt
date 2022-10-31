package com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails

import android.os.Bundle

import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentSchoolDetailsBinding
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.homeDetails.HomeDetailsFragment


class SchoolDetailsFragment :
    BaseDialogFragmentNew<FragmentSchoolDetailsBinding, SchoolDetailsView, SchoolDetailsViewModel>(),
    SchoolDetailsView {

    private lateinit var communicator: Communicator
    var isClicked: Boolean = true

    override fun getContentView(): Int = R.layout.fragment_school_details

    override fun setViewModelClass(): Class<SchoolDetailsViewModel> {
        return SchoolDetailsViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.schoolDetails

    override fun getNavigator(): SchoolDetailsView = this

    override fun initViews(savedInstanceState: Bundle?) {
        communicator = activity as Communicator
        setOnClickListener()
    }

    private fun setOnClickListener(){
        mViewDataBinding?.btnSign?.setOnClickListener {
            val homeDetailsFragment = HomeDetailsFragment()
            communicator.loadFragments(homeDetailsFragment, true, 3)
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