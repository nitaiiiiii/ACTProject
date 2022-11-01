package com.ci.act.ui.home.mainEventScreenRegister


import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMainEventScreenRegisterBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.customDialogFragments.cantAccessAccount.CantAccessAccountFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.GuardianApprovalFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.homeDetails.HomeDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.PersonalDetailsFragment
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.SchoolDetailsFragment
import kotlinx.android.synthetic.main.activity_main_event_screen_register.*


class MainEventScreenRegisterActivity :
    BaseActivity<ActivityMainEventScreenRegisterBinding, MainEventScreenRegisterView, MainEventScreenRegisterViewModel>(),
    MainEventScreenRegisterView, Communicator {
    var isSelected: Boolean = true
    var checkOnBackPress = false
    var isShowingDigitalSign = false


    override fun getContentView(): Int = R.layout.activity_main_event_screen_register

    override fun setViewModelClass(): Class<MainEventScreenRegisterViewModel> {
        return MainEventScreenRegisterViewModel::class.java
    }

    override fun getNavigator(): MainEventScreenRegisterView = this

    override fun getBindingVariable(): Int = BR.screenRegister

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
        supportFragmentManager.beginTransaction()
            .replace(R.id.signUpFragmentContainer, PersonalDetailsFragment()).commit()

    }


    private fun setOnClickListener() {
//        mViewDataBinding?.imgPersonDetails?.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.signUpFragmentContainer, HomeDetailsFragment()).commit()
//            if (isSelected) {
//                imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//                imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
//                imgPersonDetails?.setImageResource(R.drawable.person_24)
//                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
//                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
//                imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
//
//            } else {
//                imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//            }
//        }
//
//        mViewDataBinding?.imgBuildingDetails?.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.signUpFragmentContainer, PersonalDetailsFragment()).commit()
//            if (isSelected) {
//                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.person_circle)
//                imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//                imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setImageResource(R.drawable.baseline_business_24)
//                imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
//                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
//                imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
//
//            } else {
//                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.person_circle)
//                imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//                imgBuildingDetails?.setImageResource(R.drawable.baseline_business_24)
//            }
//        }
//
//        mViewDataBinding?.imgHomeDetails?.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.signUpFragmentContainer, SchoolDetailsFragment()).commit()
//            if (isSelected) {
//                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
//                imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setImageResource(R.drawable.baseline_home_24)
//                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
//                imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
//                imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
//
//
//            } else {
//                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
//            }
//        }
//
//        mViewDataBinding?.imgGuardianApproval?.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.signUpFragmentContainer, GuardianApprovalFragment()).commit()
//
//            if (isSelected) {
//                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgGuardianApproval?.setBackgroundResource(R.drawable.person_circle)
//                imgGuardianApproval?.setImageResource(R.drawable.baseline_group_24)
//                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
//                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
//                imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
//
//            } else {
//                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
//            }
//        }


    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            if (checkOnBackPress) {
//                imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
//                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
//                imgHomeDetails?.setBackgroundResource(R.drawable.builiding_details)
//                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
//                imgGuardianApproval?.setImageResource(R.drawable.baseline_group_24)
//                imgGuardianApproval?.setBackgroundResource(R.drawable.person_circle)

            }
        } else {
            super.onBackPressed()
        }

        mViewDataBinding?.txtSignatureBox?.setOnClickListener {
            val cantAccessFragment = CantAccessAccountFragment()
            if (!SignInActivity.isShowingCancelProjectDialog) {
                SignInActivity.isShowingCancelProjectDialog = true
                supportFragmentManager?.let { it1 ->
                    cantAccessFragment.show(
                        it1,
                        "Cancel Project"
                    )
                }
            }
        }
    }

    override fun loadFragments(fragment: Fragment, check: Boolean, fragmentPage: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.signUpFragmentContainer, fragment)
            .addToBackStack(null).commit()

        when (fragmentPage) {
            1 -> {
                imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
                imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                imgPersonDetails?.setImageResource(R.drawable.person_24)
                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                imgBuildingDetails?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                imgHomeDetails?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
                imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
                imgGuardianApproval?.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.light_grey
                    )
                )
            }
            2 -> {
                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgBuildingDetails?.setBackgroundResource(R.drawable.person_circle)
                imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
                imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                imgBuildingDetails?.setImageResource(R.drawable.baseline_business_24)
                imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
            }
            3 -> {
                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
                imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                imgHomeDetails?.setImageResource(R.drawable.baseline_home_24)
                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
                imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
            }
            4 -> {
                imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgHomeDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgGuardianApproval?.setBackgroundResource(R.drawable.person_circle)
                imgGuardianApproval?.setImageResource(R.drawable.baseline_group_24)
                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
            }
            else -> {
                imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
                imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
                imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                imgPersonDetails?.setImageResource(R.drawable.person_24)
                imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                imgBuildingDetails?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
                imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                imgHomeDetails?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
                imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
                imgGuardianApproval?.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.light_grey
                    )
                )

            }
        }

//        if (check) {
//            btnSignature?.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.signUpFragmentContainer, HomeDetailsFragment()).commit()
//                if (isSelected) {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//                    imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
//                    imgPersonDetails?.setImageResource(R.drawable.person_24)
//                    imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
//                    imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
//                    imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
//
//                } else {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//                }
//            }
//
//            btnSign?.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.signUpFragmentContainer, PersonalDetailsFragment()).commit()
//                if (isSelected) {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.person_circle)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//                    imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
//                    imgBuildingDetails?.setImageResource(R.drawable.baseline_business_24)
//                    imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
//                    imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
//                    imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
//
//                } else {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.person_circle)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
//                    imgBuildingDetails?.setImageResource(R.drawable.baseline_business_24)
//                }
//            }
//
//            mViewDataBinding?.imgHomeDetails?.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.signUpFragmentContainer, SchoolDetailsFragment()).commit()
//                if (isSelected) {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
//                    imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
//                    imgHomeDetails?.setImageResource(R.drawable.baseline_home_24)
//                    imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
//                    imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
//                    imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
//
//
//                } else {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
//                }
//            }
//
//            mViewDataBinding?.imgGuardianApproval?.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.signUpFragmentContainer, GuardianApprovalFragment()).commit()
//
//                if (isSelected) {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgGuardianApproval?.setBackgroundResource(R.drawable.person_circle)
//                    imgGuardianApproval?.setImageResource(R.drawable.baseline_group_24)
//                    imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
//                    imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
//                    imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
//
//                } else {
//                    imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
//                    imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
//                }
//            }
//        }
        checkOnBackPress = check


    }


}