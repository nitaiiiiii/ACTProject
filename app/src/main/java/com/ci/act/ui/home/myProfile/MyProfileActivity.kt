package com.ci.act.ui.home.myProfile

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityMyProfileBinding
import com.ci.act.ui.editProfile.EditProfileActivity
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.mysports.MySportsActivity
import com.ci.act.ui.personalInfo.PersonalInfoActivity
import com.google.android.material.chip.Chip

class MyProfileActivity :
    BaseActivity<ActivityMyProfileBinding, MyProfileView, MyProfileViewModel>(), MyProfileView {
    override fun getContentView(): Int = R.layout.activity_my_profile

    override fun setViewModelClass(): Class<MyProfileViewModel> {
        return MyProfileViewModel::class.java
    }

    override fun getNavigator(): MyProfileView = this

    override fun getBindingVariable(): Int = BR.myProfile

    override fun initViews(savedInstanceState: Bundle?) {
        setUpChips()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.imgMyProfileEvents?.setOnClickListener {
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }
        mViewDataBinding?.imgMySportsRedProfile?.setOnClickListener {
            val intent = Intent(this, MySportsActivity::class.java)
            startActivity(intent)
        }
        mViewDataBinding?.imgPersonDetails?.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        mViewDataBinding?.imgPersonalRedProfile?.setOnClickListener {
            val intent = Intent(this, PersonalInfoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpChips() {
        val chipList = ArrayList<String>()
        chipList.add("FOOTBALL")
        chipList.add("BASEBALL")
        chipList.add("SOCCER")
        chipList.add("BASKETBALL")

        for (i in chipList.indices) {
            val chip = Chip(
                ContextThemeWrapper(
                    this,
                    com.google.android.material.R.style.Theme_MaterialComponents_Light
                )
            )
            chip.setTextColor(ContextCompat.getColor(this, R.color.white))
            chip.text = chipList[i]
            if (i % 2 == 0) {
                chip.chipBackgroundColor =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.ruby_red))
            } else {
                chip.chipBackgroundColor =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.orange))
            }

            mViewDataBinding?.chipGroups?.addView(chip)
        }
    }


}