package com.ci.act.ui.editProfile

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityEditProfileBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.changePassword.ChangePasswordActivity
import com.ci.act.ui.customDialogFragments.cantAccessAccount.CantAccessAccountFragment
import com.ci.act.ui.customDialogFragments.permanentDelete.PermanentDeleteFragment
import com.ci.act.ui.home.leaderBoard.adapter.BottomSheetRecyclerAdapter
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.model.BottomSheetModel
import com.ci.act.ui.home.myProfile.MyProfileActivity
import com.ci.act.widgets.CustomEditText
import com.ci.act.widgets.CustomTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*
import kotlin.collections.ArrayList

class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding, EditProfileView, EditProfileViewModel>(),
    EditProfileView {
    private var bottomSheetDialog: BottomSheetDialog? = null
    private val graduationArray: ArrayList<BottomSheetModel> = ArrayList()
    var calendarDay = 0
    var calendarMonth = 0
    var calendarYear = 0

    override fun getContentView(): Int = R.layout.activity_edit_profile

    override fun setViewModelClass(): Class<EditProfileViewModel> {
        return EditProfileViewModel::class.java
    }

    companion object {
        var isShowingDelete = false
    }

    override fun getNavigator(): EditProfileView = this

    override fun getBindingVariable(): Int = BR.editProfile

    override fun initViews(savedInstanceState: Bundle?) {

        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.txtChangePassword?.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        mViewDataBinding?.editTextProfileYear?.setOnClickListener {
            showGraduationListBottomSheet(mViewDataBinding?.editTextProfileYear!!)
        }

        mViewDataBinding?.editTextProfileDateOfBirth?.setOnClickListener {
            getDateTimeCalender()
        }

        mViewDataBinding?.txtDeleteMyAccount?.setOnClickListener {
            val permanentDeleteFragment = PermanentDeleteFragment()
            if (!EditProfileActivity.isShowingDelete) {
                EditProfileActivity.isShowingDelete = true
                supportFragmentManager?.let { it1 ->
                    permanentDeleteFragment.show(
                        it1,
                        "Permanent Delete"
                    )
                }
            }
        }

            mViewDataBinding?.btnEditProfileMale?.setOnClickListener {
                mViewDataBinding?.btnEditProfileMale?.setBackgroundResource(R.drawable.btn_round_background)
                mViewDataBinding?.btnEditProfileMale?.setTextColor(ContextCompat.getColor(this, R.color.textColor))
                mViewDataBinding?.btnEditProfileMale!!.compoundDrawables[0].setTint(ContextCompat.getColor(this, R.color.textColor))
                mViewDataBinding?.btnEditProfileFemale?.setBackgroundResource(R.drawable.shape_btn_outline)
                mViewDataBinding?.btnEditProfileFemale?.setTextColor(ContextCompat.getColor(this, R.color.black))
                mViewDataBinding?.btnEditProfileFemale!!.compoundDrawables[0].setTint(ContextCompat.getColor(this, R.color.black))
            }

            mViewDataBinding?.btnEditProfileFemale?.setOnClickListener {
                mViewDataBinding?.btnEditProfileFemale?.setBackgroundResource(R.drawable.btn_round_background)
                mViewDataBinding?.btnEditProfileFemale?.setTextColor(ContextCompat.getColor(this, R.color.textColor))
                mViewDataBinding?.btnEditProfileFemale!!.compoundDrawables[0].setTint(ContextCompat.getColor(this, R.color.textColor))
                mViewDataBinding?.btnEditProfileMale?.setBackgroundResource(R.drawable.shape_btn_outline)
                mViewDataBinding?.btnEditProfileMale?.setTextColor(ContextCompat.getColor(this, R.color.black))
                mViewDataBinding?.btnEditProfileMale!!.compoundDrawables[0].setTint(ContextCompat.getColor(this, R.color.black))
            }

    }

    private fun showGraduationListBottomSheet(editText: CustomEditText) {
        if (bottomSheetDialog?.isShowing == true) {
            bottomSheetDialog?.dismiss()
        }

        bottomSheetDialog = BottomSheetDialog(this, R.style.BaseBottomSheetDialog)
        val layoutView: View = LayoutInflater.from(this)
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
        popUpHeader?.text = "Select Year Of Graduation"
        recyclerViewPopup?.layoutManager = LinearLayoutManager(this)
        val bottomSheetSelectAdapter = BottomSheetRecyclerAdapter()
        recyclerViewPopup?.adapter = bottomSheetSelectAdapter
        bottomSheetSelectAdapter.setOnClickListener(object : BottomSheetRecyclerAdapter.OnClick {
            override fun onClick(bottomSheetArray: BottomSheetModel) {
                setBottomSheetValue(bottomSheetArray.yearOfGraduation ?: "", editText)
                bottomSheetDialog?.dismiss()
            }
        })

        addGraduationData()

        bottomSheetSelectAdapter.addList(graduationArray, mViewDataBinding?.editTextProfileYear?.text?.toString())

        bottomSheetDialog?.window?.attributes?.windowAnimations = R.style.BaseBottomSheetDialog
        bottomSheetDialog?.show()
        val selectedValue: String? =
            if ((mViewDataBinding?.editTextProfileYear?.text?.length
                    ?: 0) > 0
            ) mViewDataBinding?.editTextProfileYear?.text?.toString() else ""
        if ((selectedValue?.length ?: 0) > 0) {
            recyclerViewPopup?.scrollToPosition(getPositionByValue(selectedValue))
        }
    }

    private fun addGraduationData() {
        graduationArray.clear()
        graduationArray.add(BottomSheetModel("1", "2022"))
        graduationArray.add(BottomSheetModel("2", "2021"))
        graduationArray.add(BottomSheetModel("3", "2020"))
        graduationArray.add(BottomSheetModel("4", "2019"))
        graduationArray.add(BottomSheetModel("5", "2018"))
        graduationArray.add(BottomSheetModel("6", "2017"))
        graduationArray.add(BottomSheetModel("7", "2016"))
        graduationArray.add(BottomSheetModel("8", "2015"))
        graduationArray.add(BottomSheetModel("9", "2014"))
        graduationArray.add(BottomSheetModel("10", "2013"))
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

    private fun getDateTimeCalender() {
        val calendar = Calendar.getInstance()
        calendarDay = calendar.get(Calendar.DAY_OF_MONTH)
        calendarMonth = calendar.get(Calendar.MONTH)
        calendarYear = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(
            this,
            R.style.DatePickerTheme_Dark,
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
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
                mViewDataBinding?.editTextProfileDateOfBirth?.setText(date)
            },
            calendarYear,
            calendarMonth,
            calendarDay
        ).let {
            it.show()
            it.setTitle("Date of Birth")
            it.setMessage("Date of Birth")
            it.datePicker.maxDate = System.currentTimeMillis()
        }
    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let {
            it.txtToolbarHeading.text = "EDIT PROFILE"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_close)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.black))
            it.imgToolBarRight.visibility = View.INVISIBLE

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this, MyProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }


}