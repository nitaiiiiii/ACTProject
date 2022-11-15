package com.ci.act.ui.customDialogFragments.requiredDetails

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentRequiredDetailsBinding
import com.ci.act.ui.authentication.forgotPassword.ForgotPasswordActivity
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.authentication.signup.SignUpActivity
import com.ci.act.ui.authentication.socialMedia.SocialMediaActivity
import com.ci.act.util.showSnackBar
import java.util.*

class RequiredDetailsFragment :
    BaseDialogFragmentNew<FragmentRequiredDetailsBinding, RequiredDetailsView, RequiredDetailsViewModel>(),
    RequiredDetailsView {

    var calendarDay = 0
    var calendarMonth = 0
    var calendarYear = 0

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
        mViewDataBinding?.btnRequiredLocation?.setOnClickListener {
            if (mViewDataBinding?.editTextRequiredDetailsEmail?.text?.isEmpty()!!) {
                showSnackBar(requireView(), "Email Address is required...", false)
                mViewDataBinding?.editTextRequiredDetailsEmail?.requestFocus()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    mViewDataBinding?.editTextRequiredDetailsEmail?.text?.trim().toString()
                ).matches()
            ) {
                showSnackBar(requireView(), "Not a valid email address...", false)
                mViewDataBinding?.editTextRequiredDetailsEmail?.requestFocus()
            } else if (mViewDataBinding?.editTextRequiredDetailsDateOfBirth?.text?.isEmpty()!!) {
                showSnackBar(requireView(), "Date of Birth is required...", false)
            } else {
                dialog?.dismiss()
                ForgotPasswordActivity.isRequiredDetails = false
                val intent = Intent(requireActivity(),SocialMediaActivity::class.java)
                startActivity(intent)



            }
        }

        mViewDataBinding?.editTextRequiredDetailsDateOfBirth?.setOnClickListener {
            getDateTimeCalender()
        }
    }

    private fun getDateTimeCalender() {
        val calendar = Calendar.getInstance()
        calendarDay = calendar.get(Calendar.DAY_OF_MONTH)
        calendarMonth = calendar.get(Calendar.MONTH)
        calendarYear = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(requireContext(), R.style.DatePickerTheme_Dark, DatePickerDialog.OnDateSetListener { view, year, month, day ->
            val month = month + 1
            var sMonth = ""
            var sDay = ""
            sMonth = if (month < 10) {
                "0$month"
            } else {
                month.toString()
            }
            sDay = if (day < 10) {
                "0$day"
            } else {
                day.toString()
            }
            val date = "$sMonth/$sDay/$year"
            mViewDataBinding?.editTextRequiredDetailsDateOfBirth?.setText(date)
        }, calendarYear, calendarMonth, calendarDay).let{
            it.show()
            it.setTitle("Date of Birth")
            it.setMessage("Date of Birth")
            it.datePicker.maxDate = System.currentTimeMillis()
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