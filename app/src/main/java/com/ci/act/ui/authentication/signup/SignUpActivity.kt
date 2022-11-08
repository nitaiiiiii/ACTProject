package com.ci.act.ui.authentication.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivitySignUpBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import java.util.*

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpView, SignUpViewModel>(),
    SignUpView {

    var calendarDay = 0
    var calendarMonth = 0
    var calendarYear = 0
    override fun getContentView(): Int = R.layout.activity_sign_up

    override fun setViewModelClass(): Class<SignUpViewModel> {
        return SignUpViewModel::class.java
    }

    override fun getNavigator(): SignUpView = this

    override fun getBindingVariable(): Int = BR.signUp

    override fun initViews(savedInstanceState: Bundle?) {
        spannableSignUpStrings()
        setOnClickListener()
        validateFields()
    }

    private fun spannableSignUpStrings() {
        val text = "Sign In"

        val spannableString = SpannableString(text).apply {
            setSpan(UnderlineSpan(), 0, text.length, 0)
        }
        mViewDataBinding?.txtSignInEmailSignUpScreen?.text = spannableString

    }

    private fun setOnClickListener() {
        mViewDataBinding?.txtSignInEmailSignUpScreen?.setOnClickListener {
            val action = Intent(this, SignInActivity::class.java)
            startActivity(action)
            finish()
        }
        mViewDataBinding?.editTextDateOfBirth?.setOnClickListener {
            getDateTimeCalender()
        }
    }

    private fun getDateTimeCalender() {
        val calendar = Calendar.getInstance()
        calendarDay = calendar.get(Calendar.DAY_OF_MONTH)
        calendarMonth = calendar.get(Calendar.MONTH)
        calendarYear = calendar.get(Calendar.YEAR)

        DatePickerDialog(this, R.style.DatePickerTheme_Dark, DatePickerDialog.OnDateSetListener { view, year, month, day ->
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
            mViewDataBinding?.editTextDateOfBirth?.setText(date)
        }, calendarYear, calendarMonth, calendarDay).let{
            it.show()
            it.setTitle("Date of Birth")
            it.setMessage("Date of Birth")
            it.datePicker.maxDate = System.currentTimeMillis()
        }
    }


    private fun validateFields() {
        mViewDataBinding?.btnSignUp?.setOnClickListener {
            if (mViewDataBinding?.editTextFirstName?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextFirstName?.requestFocus()
                showErrorMessage(
                    "First Name is Required..."
                )
            } else if (mViewDataBinding?.editTextLastName?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextLastName?.requestFocus()
                showErrorMessage(
                    "Last Name is Required..."
                )
            } else if (mViewDataBinding?.editTextDateOfBirth?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextDateOfBirth?.requestFocus()
                showErrorMessage(
                    "Date Selection is Required..."
                )
            } else if (mViewDataBinding?.editTextEmailAddress?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextEmailAddress?.requestFocus()
                showErrorMessage(
                    "Email Address is Required..."
                )
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                    mViewDataBinding?.editTextEmailAddress?.text?.trim().toString()
                ).matches()
            ) {
                mViewDataBinding?.editTextEmailAddress?.requestFocus()
                showErrorMessage(
                    "Not a valid Email address..."
                )
            } else if (mViewDataBinding?.editTextPassword?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextPassword?.requestFocus()
                showErrorMessage(
                    "Password is required..."
                )
            } else if (mViewDataBinding?.editTextPassword?.text?.length!! < 8 || mViewDataBinding?.editTextPassword?.text?.length!! >= 16) {
                mViewDataBinding?.editTextPassword?.requestFocus()
                showErrorMessage(
                    "Password must be 8 to 16 characters..."
                )

            } else {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }

    }


}