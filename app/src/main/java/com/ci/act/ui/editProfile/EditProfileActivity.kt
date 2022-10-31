package com.ci.act.ui.editProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityEditProfileBinding

class EditProfileActivity: BaseActivity<ActivityEditProfileBinding,EditProfileView,EditProfileViewModel>(),EditProfileView {
    override fun getContentView(): Int = R.layout.activity_edit_profile

    override fun setViewModelClass(): Class<EditProfileViewModel> {
        return EditProfileViewModel::class.java
    }

    override fun getNavigator(): EditProfileView = this

    override fun getBindingVariable(): Int = BR.editProfile

    override fun initViews(savedInstanceState: Bundle?) {
    }

}