package com.ci.act.ui.changePassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityChangePasswordBinding
import com.ci.act.ui.editProfile.EditProfileActivity

class ChangePasswordActivity :
    BaseActivity<ActivityChangePasswordBinding, ChangePasswordView, ChangePasswordViewModel>(),
    ChangePasswordView {
    override fun getContentView(): Int = R.layout.activity_change_password

    override fun setViewModelClass(): Class<ChangePasswordViewModel> {
        return ChangePasswordViewModel::class.java
    }

    override fun getNavigator(): ChangePasswordView = this

    override fun getBindingVariable(): Int = BR.changePassword

    override fun initViews(savedInstanceState: Bundle?) {
        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener(){

    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let {
            it.txtToolbarHeading.text = "CHANGE PASSWORD"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_close)
            it.imgToolBarLeft.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.light_black
                )
            )
            it.imgToolBarRight.visibility = View.INVISIBLE

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this,EditProfileActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
    }

}