package com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentSchoolDetailsBinding
import com.ci.act.ui.home.leaderBoard.adapter.BottomSheetRecyclerAdapter
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.homeDetails.HomeDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.model.BottomSheetModel
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.adapter.CountryBottomSheetAdapter
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.model.CountryModel
import com.ci.act.util.showSnackBar
import com.ci.act.widgets.CustomEditText
import com.ci.act.widgets.CustomTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SchoolDetailsFragment :
    BaseDialogFragmentNew<FragmentSchoolDetailsBinding, SchoolDetailsView, SchoolDetailsViewModel>(),
    SchoolDetailsView {

    private lateinit var communicator: Communicator
    var isClicked: Boolean = true
    private var bottomSheetDialog: BottomSheetDialog? = null
    private val graduationArray: ArrayList<BottomSheetModel> = ArrayList()
    private var countryArray = ArrayList<CountryModel.CountryModelItem>()

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

    private fun setOnClickListener() {
        mViewDataBinding?.btnSign?.setOnClickListener {
          validatePersonalDetails()
        }

        mViewDataBinding?.editTextEmailAddress?.setOnClickListener {
            showGraduationListBottomSheet(mViewDataBinding?.editTextEmailAddress!!)
        }

        mViewDataBinding?.editTextPosition?.setOnClickListener{
            showCountryListBottomSheet(mViewDataBinding?.editTextPosition!!)
        }


    }

    private fun validatePersonalDetails() {
        mViewDataBinding?.btnSign?.setOnClickListener {
            if (mViewDataBinding?.editTextFirstName?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextFirstName?.requestFocus()
                showSnackBar(requireView(), "School name is required...", false)
            } else if (mViewDataBinding?.editTextLastName?.text?.isEmpty() == true) {
                mViewDataBinding?.editTextLastName?.requestFocus()
                showSnackBar(requireView(), "Grade is required...", false)

            } else if (mViewDataBinding?.editTextState?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "State is required...", false)

            } else if (mViewDataBinding?.editTextState?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "City is required...", false)

            } else if (mViewDataBinding?.editTextState?.text?.isEmpty() == true) {
                showSnackBar(requireView(), "Zip Code is required...", false)

            }  else {
                val homeDetailsFragment = HomeDetailsFragment()
                communicator.loadFragments(homeDetailsFragment, true, 3)
            }
        }

    }


    private fun showGraduationListBottomSheet(editText: CustomEditText) {
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
        popUpHeader?.text = "Select Year Of Graduation"
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

        bottomSheetSelectAdapter.addList(graduationArray, mViewDataBinding?.editTextEmailAddress?.text?.toString())

        bottomSheetDialog?.window?.attributes?.windowAnimations = R.style.BaseBottomSheetDialog
        bottomSheetDialog?.show()
        val selectedValue: String? =
            if ((mViewDataBinding?.editTextEmailAddress?.text?.length
                    ?: 0) > 0
            ) mViewDataBinding?.editTextEmailAddress?.text?.toString() else ""
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

    private fun showCountryListBottomSheet(editText: CustomEditText) {
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
        popUpHeader?.text = "Select Country"
        recyclerViewPopup?.layoutManager = LinearLayoutManager(requireContext())
        val bottomSheetSelectAdapter = CountryBottomSheetAdapter()
        recyclerViewPopup?.adapter = bottomSheetSelectAdapter
        bottomSheetSelectAdapter.setOnClickListener(object : CountryBottomSheetAdapter.OnClick {
            override fun onClick(bottomSheetArray: CountryModel.CountryModelItem) {
                setBottomSheetValue(bottomSheetArray.countryName ?: "", editText)
                bottomSheetDialog?.dismiss()
            }
        })

        addJsonArray()

        bottomSheetSelectAdapter.addList(countryArray, mViewDataBinding?.editTextPosition?.text?.toString())

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
            jsonString = requireContext().assets.open("countries_list.json").bufferedReader().use { it.readText() }
            jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            jsonString
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