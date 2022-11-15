package com.ci.act.ui.home.mainEventScreenRegister.fragments.homeDetails


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentHomeDetailsBinding
import com.ci.act.ui.home.leaderBoard.adapter.BottomSheetRecyclerAdapter
import com.ci.act.ui.home.mainEventScreenRegister.Communicator
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.PersonalDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.model.BottomSheetModel
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.SchoolDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.adapter.CountryBottomSheetAdapter
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.model.CountryModel
import com.ci.act.util.showSnackBar
import com.ci.act.widgets.CustomEditText
import com.ci.act.widgets.CustomTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeDetailsFragment :
    BaseDialogFragmentNew<FragmentHomeDetailsBinding, HomeDetailsView, HomeDetailsViewModel>(),
    HomeDetailsView {
    private lateinit var communicator: Communicator
    private var bottomSheetDialog: BottomSheetDialog? = null
    private val graduationArray: ArrayList<BottomSheetModel> = ArrayList()
    private var countryArray = ArrayList<CountryModel.CountryModelItem>()

    override fun getContentView(): Int = R.layout.fragment_home_details

    override fun setViewModelClass(): Class<HomeDetailsViewModel> {
        return HomeDetailsViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.homeDetails

    override fun getNavigator(): HomeDetailsView = this

    override fun initViews(savedInstanceState: Bundle?) {
        communicator = activity as Communicator
        setOnClickListener()
        setTextWatchers()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.btnSignatureBox?.setOnClickListener {
            validatePersonalDetails()
        }
        mViewDataBinding?.editTextPosition?.setOnClickListener{
            showCountryListBottomSheet(mViewDataBinding?.editTextPosition!!)
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

    private fun setTextWatchers() {
        mViewDataBinding?.editTextCoder?.addTextChangedListener(com.ci.act.util.PhoneNumberFormattingTextWatcher(mViewDataBinding?.editTextCoder, null))

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