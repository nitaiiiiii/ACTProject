package com.ci.act.ui.home.mainEventScreenRegister

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMainEventScreenRegisterBinding
import com.ci.act.ui.home.mainEventScreenRegister.fragments.personalDetails.PersonalDetailsFragment

class MainEventScreenRegisterActivity : BaseActivity<ActivityMainEventScreenRegisterBinding, MainEventScreenRegisterView, MainEventScreenRegisterViewModel>(), MainEventScreenRegisterView, Communicator {

    private var fragmentPageNumber = 1

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
        mViewDataBinding?.imgBackArrow?.setOnClickListener {
            fragmentPageNumber -= 1
            checkStatus(fragmentPageNumber)
            onBackPressed()
        }

        mViewDataBinding?.imgCancel?.setOnClickListener {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun checkStatus(fragmentPageNumber: Int) {
        when (fragmentPageNumber) {
            1 -> {
                mViewDataBinding?.imgBackArrow?.visibility = View.GONE
                mViewDataBinding?.imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
                mViewDataBinding?.imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
                mViewDataBinding?.imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                mViewDataBinding?.imgPersonDetails?.setImageResource(R.drawable.person_24)
                mViewDataBinding?.imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                mViewDataBinding?.imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
            }
            2 -> {
                mViewDataBinding?.imgBackArrow?.visibility = View.VISIBLE
                mViewDataBinding?.imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgBuildingDetails?.setBackgroundResource(R.drawable.person_circle)
                mViewDataBinding?.imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
                mViewDataBinding?.imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgBuildingDetails?.setImageResource(R.drawable.baseline_business_24)
                mViewDataBinding?.imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
                mViewDataBinding?.imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                mViewDataBinding?.imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
            }
            3 -> {
                mViewDataBinding?.imgBackArrow?.visibility = View.VISIBLE
                mViewDataBinding?.imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgHomeDetails?.setBackgroundResource(R.drawable.person_circle)
                mViewDataBinding?.imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgHomeDetails?.setImageResource(R.drawable.baseline_home_24)
                mViewDataBinding?.imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                mViewDataBinding?.imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
                mViewDataBinding?.imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
            }
            4 -> {
                mViewDataBinding?.imgBackArrow?.visibility = View.VISIBLE
                mViewDataBinding?.imgPersonDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgHomeDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgGuardianApproval?.setBackgroundResource(R.drawable.person_circle)
                mViewDataBinding?.imgGuardianApproval?.setImageResource(R.drawable.baseline_group_24)
                mViewDataBinding?.imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                mViewDataBinding?.imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                mViewDataBinding?.imgPersonDetails?.setImageResource(R.drawable.ic_baseline_person_24)
            }
            else -> {
                mViewDataBinding?.imgBackArrow?.visibility = View.GONE
                mViewDataBinding?.imgPersonDetails?.setBackgroundResource(R.drawable.person_circle)
                mViewDataBinding?.imgBuildingDetails?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgHomeDetails?.setBackgroundResource(R.drawable.house_details)
                mViewDataBinding?.imgGuardianApproval?.setBackgroundResource(R.drawable.builiding_details)
                mViewDataBinding?.imgPersonDetails?.setImageResource(R.drawable.person_24)
                mViewDataBinding?.imgBuildingDetails?.setImageResource(R.drawable.ic_baseline_business_24)
                mViewDataBinding?.imgBuildingDetails?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
                mViewDataBinding?.imgHomeDetails?.setImageResource(R.drawable.ic_baseline_home_24)
                mViewDataBinding?.imgHomeDetails?.setColorFilter(ContextCompat.getColor(                     this, R.color.light_grey))
                mViewDataBinding?.imgGuardianApproval?.setImageResource(R.drawable.ic_baseline_group_24)
                mViewDataBinding?.imgGuardianApproval?.setColorFilter(ContextCompat.getColor(this, R.color.light_grey))
            }

        }
    }

    override fun loadFragments(fragment: Fragment, check: Boolean, fragmentPage: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.signUpFragmentContainer, fragment)
            .addToBackStack(null).commit()

        fragmentPageNumber = fragmentPage

        checkStatus(fragmentPageNumber)
    }
}