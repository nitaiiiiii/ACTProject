package com.ci.act.ui.editProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityEditProfileBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.changePassword.ChangePasswordActivity
import com.ci.act.ui.customDialogFragments.cantAccessAccount.CantAccessAccountFragment
import com.ci.act.ui.customDialogFragments.permanentDelete.PermanentDeleteFragment

class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding, EditProfileView, EditProfileViewModel>(),
    EditProfileView {
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
    }

    private fun setOnClickListener() {
        mViewDataBinding?.txtChangePassword?.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        mViewDataBinding?.txtDeleteMyAccount?.setOnClickListener {
            val permanentDeleteFragment = PermanentDeleteFragment()
            if (!EditProfileActivity.isShowingDelete) {
                EditProfileActivity.isShowingDelete = true
                supportFragmentManager?.let { it1 ->
                    permanentDeleteFragment.show(
                        it1,
                        "Cancel Project"
                    )
                }
            }
        }
    }


}