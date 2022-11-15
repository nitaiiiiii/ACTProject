package com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentPersonalDetailsBinding
import com.ci.act.ui.home.leaderBoard.adapter.BottomSheetRecyclerAdapter
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.homeDetails.HomeDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.model.BottomSheetModel
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.SchoolDetailsFragment
import com.ci.act.util.showSnackBar
import com.ci.act.widgets.CustomEditText
import com.ci.act.widgets.CustomTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main_event_screen_register.*
import kotlinx.android.synthetic.main.fragment_personal_details.*
import kotlinx.coroutines.selects.select

class PersonalDetailsFragment :
    BaseDialogFragmentNew<FragmentPersonalDetailsBinding, PersonalDetailsView, PersonalDetailsViewModel>(),
    PersonalDetailsView {

    private var bottomSheetDialog: BottomSheetDialog? = null
    private val graduationArray: ArrayList<BottomSheetModel> = ArrayList()
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
        setTextWatchers()


    }

    private fun setOnClickListener() {
        mViewDataBinding?.btnMale?.setOnClickListener {
            mViewDataBinding?.btnMale?.setBackgroundResource(R.drawable.btn_round_background)
            mViewDataBinding?.btnMale?.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
            mViewDataBinding?.btnMale!!.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(), R.color.textColor))
            mViewDataBinding?.btnFemale?.setBackgroundResource(R.drawable.shape_btn_outline)
            mViewDataBinding?.btnFemale?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            mViewDataBinding?.btnFemale!!.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(), R.color.black))
        }

        mViewDataBinding?.btnFemale?.setOnClickListener {
            mViewDataBinding?.btnFemale?.setBackgroundResource(R.drawable.btn_round_background)
            mViewDataBinding?.btnFemale?.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
            mViewDataBinding?.btnFemale!!.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(), R.color.textColor))
            mViewDataBinding?.btnMale?.setBackgroundResource(R.drawable.shape_btn_outline)
            mViewDataBinding?.btnMale?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            mViewDataBinding?.btnMale!!.compoundDrawables[0].setTint(ContextCompat.getColor(requireContext(), R.color.black))
        }

        mViewDataBinding?.editTextPosition?.setOnClickListener{
            showPositionListBottomSheet(mViewDataBinding?.editTextPosition!!)

        }

    }

    private fun setTextWatchers() {
        mViewDataBinding?.editTextMobileNumber?.addTextChangedListener(com.ci.act.util.PhoneNumberFormattingTextWatcher(mViewDataBinding?.editTextMobileNumber, null))

        mViewDataBinding?.editTextPolicyNumber?.addTextChangedListener(object : TextWatcher {

            var keyDel :Int?= null

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewDataBinding?.editTextPolicyNumber?.setOnKeyListener(object : View.OnKeyListener {
                    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                        if (keyCode == KeyEvent.KEYCODE_DEL) keyDel = 1
                        return false
                    }
                })

                if (keyDel == 0) {
                    val len: Int = mViewDataBinding?.editTextPolicyNumber?.text?.length!!
                    if (len == 3 || len == 6) {
                        mViewDataBinding?.editTextPolicyNumber?.setText(mViewDataBinding?.editTextPolicyNumber?.text.toString() + "-")
                        mViewDataBinding?.editTextPolicyNumber?.setSelection(mViewDataBinding?.editTextPolicyNumber?.text?.length!!)
                    }
                } else {
                    keyDel = 0
                }
//                if(abs(count - before) == 1){
//                    mViewDataBinding?.etPolicy?.setText(policyNumberFormat(s?.trim().toString()))
//                }
//                mViewDataBinding?.etPolicy?.setSelection(mViewDataBinding?.etPolicy?.text?.length!!)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun policyNumberFormat(policy: String): String {
        var result = ""
        for (p in policy.indices) {
            if (p == 2) {
                result += "${policy[p]}-"
            } else if (p == 5) {
                result += "${policy[p]}-"
            } else {
                result += policy[p]
            }
        }
        return result
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

    private fun showPositionListBottomSheet(editText: CustomEditText) {
        if (bottomSheetDialog?.isShowing == true) {
            bottomSheetDialog?.dismiss()
        }

        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BaseBottomSheetDialog)
        val layoutView: View = LayoutInflater.from(requireContext())
            .inflate(R.layout.inflate_bottom_spinner_dialog, null, false)
        bottomSheetDialog?.setContentView(layoutView)
        bottomSheetDialog?.setCancelable(true)
        val recyclerViewPopup: RecyclerView? =
            layoutView.findViewById(R.id.rvBottomSheetSpinnerDialogPopup)
        val popUpHeader: CustomTextView? = layoutView?.findViewById(R.id.bottomSheetPopUpHeader)
        val popUpCloseIcon: ImageView? = layoutView?.findViewById(R.id.bottomSheetPopUpCloseIcon)
        popUpCloseIcon?.setOnClickListener {
            bottomSheetDialog?.dismiss()
        }
        popUpHeader?.text = "Select Position"
        recyclerViewPopup?.layoutManager = LinearLayoutManager(requireContext())
        val bottomSheetSelectAdapter = BottomSheetRecyclerAdapter()
        recyclerViewPopup?.adapter = bottomSheetSelectAdapter
        bottomSheetSelectAdapter.setOnClickListener(object : BottomSheetRecyclerAdapter.OnClick {
            override fun onClick(bottomSheetArray: BottomSheetModel) {
                setBottomSheetValue(bottomSheetArray.yearOfGraduation ?: "", editText)
                bottomSheetDialog?.dismiss()
            }
        })

        addGraduationData()

        bottomSheetSelectAdapter.addList(graduationArray, mViewDataBinding?.editTextPosition?.text?.toString())

        bottomSheetDialog?.window?.attributes?.windowAnimations = R.style.BaseBottomSheetDialog
        bottomSheetDialog?.show()
        val selectedValue: String? =
            if ((mViewDataBinding?.editTextPosition?.text?.length
                    ?: 0) > 0
            ) mViewDataBinding?.editTextPosition?.text?.toString() else ""
        if ((selectedValue?.length ?: 0) > 0) {
            recyclerViewPopup?.scrollToPosition(getPositionByValue(selectedValue))
        }
    }

    private fun addGraduationData() {
        graduationArray.clear()
        graduationArray.add(BottomSheetModel("1", "Athletic Trainer"))
        graduationArray.add(BottomSheetModel("2", "Coach"))
        graduationArray.add(BottomSheetModel("3", "Assistant Coach"))
        graduationArray.add(BottomSheetModel("4", "Associate Athletic Director"))
        graduationArray.add(BottomSheetModel("5", "Athletic Director"))
        graduationArray.add(BottomSheetModel("6", "Physical Education Instructor"))
        graduationArray.add(BottomSheetModel("7", "Marketing and Promotions Coordinator"))
        graduationArray.add(BottomSheetModel("8", "Athletic Program Development Director"))
        graduationArray.add(BottomSheetModel("9", "Sports Information Director"))
        graduationArray.add(BottomSheetModel("10", "Physical Therapist"))
    }

    private fun setBottomSheetValue(value: String, editText: CustomEditText) {
        editText.setText(value)
    }

    private fun getPositionByValue(value: String?): Int {
        for ((pos, i) in graduationArray.withIndex()) {
            if (i.yearOfGraduation == value) {
                return if (pos == 0) 0 else pos - 1
            }
        }
        return 0
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