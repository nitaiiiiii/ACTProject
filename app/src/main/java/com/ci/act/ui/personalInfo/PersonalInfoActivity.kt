package com.ci.act.ui.personalInfo


import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityPersonalInfoBinding

class PersonalInfoActivity :
    BaseActivity<ActivityPersonalInfoBinding, PersonalInfoView, PersonalInfoViewModel>(),
    PersonalInfoView {
    override fun getContentView(): Int = R.layout.activity_personal_info

    override fun setViewModelClass(): Class<PersonalInfoViewModel> {
        return PersonalInfoViewModel::class.java
    }

    override fun getNavigator(): PersonalInfoView = this

    override fun getBindingVariable(): Int = BR.personalInfo

    override fun initViews(savedInstanceState: Bundle?) {
    }

}