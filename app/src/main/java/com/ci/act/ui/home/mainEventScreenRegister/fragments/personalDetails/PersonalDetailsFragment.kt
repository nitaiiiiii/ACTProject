package com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentPersonalDetailsBinding
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.SchoolDetailsFragment
import kotlinx.android.synthetic.main.activity_main_event_screen_register.*
import kotlinx.android.synthetic.main.fragment_personal_details.*

class PersonalDetailsFragment:BaseDialogFragmentNew<FragmentPersonalDetailsBinding,PersonalDetailsView,PersonalDetailsViewModel>(),PersonalDetailsView {

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


    }

    private fun setOnClickListener(){
        mViewDataBinding?.btnSignature?.setOnClickListener{
            val schoolDetails = SchoolDetailsFragment()
            communicator.loadFragments(schoolDetails, true, 2)

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