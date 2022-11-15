package com.ci.act.ui.personalInfo


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityPersonalInfoBinding
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.model.BottomSheetModel
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.adapter.CountryBottomSheetAdapter
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.model.CountryModel
import com.ci.act.util.PhoneNumberFormattingTextWatcher
import com.ci.act.widgets.CustomEditText
import com.ci.act.widgets.CustomTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PersonalInfoActivity :
    BaseActivity<ActivityPersonalInfoBinding, PersonalInfoView, PersonalInfoViewModel>(),
    PersonalInfoView {

    private var bottomSheetDialog: BottomSheetDialog? = null
    private val graduationArray: ArrayList<BottomSheetModel> = ArrayList()
    private var countryArray = ArrayList<CountryModel.CountryModelItem>()

    override fun getContentView(): Int = R.layout.activity_personal_info

    override fun setViewModelClass(): Class<PersonalInfoViewModel> {
        return PersonalInfoViewModel::class.java
    }

    override fun getNavigator(): PersonalInfoView = this

    override fun getBindingVariable(): Int = BR.personalInfo

    override fun initViews(savedInstanceState: Bundle?) {

        mViewDataBinding?.editPersonalInfoTextCountry?.setOnClickListener {
            showCountryListBottomSheet(mViewDataBinding?.editPersonalInfoTextCountry!!)
        }
        setTextWatchers()
    }


    private fun setTextWatchers() {
        mViewDataBinding?.editPersonalInfoTextPolicyNumber?.addTextChangedListener(
            PhoneNumberFormattingTextWatcher(mViewDataBinding?.editPersonalInfoTextPolicyNumber, null)
        )
        mViewDataBinding?.editPersonalInfoTextFirstName?.addTextChangedListener(
            PhoneNumberFormattingTextWatcher(mViewDataBinding?.editPersonalInfoTextFirstName, null)
        )
    }

    private fun showCountryListBottomSheet(editText: CustomEditText) {
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
        popUpHeader?.text = "Select Country"
        recyclerViewPopup?.layoutManager = LinearLayoutManager(this)
        val bottomSheetSelectAdapter = CountryBottomSheetAdapter()
        recyclerViewPopup?.adapter = bottomSheetSelectAdapter
        bottomSheetSelectAdapter.setOnClickListener(object : CountryBottomSheetAdapter.OnClick {
            override fun onClick(bottomSheetArray: CountryModel.CountryModelItem) {
                setBottomSheetValue(bottomSheetArray.countryName ?: "", editText)
                bottomSheetDialog?.dismiss()
            }
        })

        addJsonArray()

        bottomSheetSelectAdapter.addList(countryArray, mViewDataBinding?.editPersonalInfoTextCountry?.text?.toString())

        bottomSheetDialog?.window?.attributes?.windowAnimations = R.style.BaseBottomSheetDialog
        bottomSheetDialog?.show()
        val selectedValue: String? =
            if ((mViewDataBinding?.editPersonalInfoTextCountry?.text?.length
                    ?: 0) > 0
            ) mViewDataBinding?.editPersonalInfoTextCountry?.text?.toString() else ""
        if ((selectedValue?.length ?: 0) > 0) {
            recyclerViewPopup?.scrollToPosition(getPositionByValue(selectedValue))
        }
    }

    private fun addJsonArray() {
        val jsonString = getJsonData()
        if (jsonString != null) {
            val jsonType = object : TypeToken<CountryModel>() {}.type
            val gson = Gson()
            countryArray.clear()
            countryArray = gson.fromJson(jsonString, jsonType)
            Log.e("json-model", "${countryArray.size}")
        }
    }

    private fun getJsonData(): String? {
        var jsonString: String? = null
        return try {
            jsonString = this.assets.open("countries_list.json").bufferedReader().use { it.readText() }
            jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            jsonString
        }
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

}