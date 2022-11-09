package com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ci.act.base.BaseViewModel
import com.ci.act.ui.home.mainEventScreenRegister.fragments.guardianApproval.page.SignatureModel

class GuardianApprovalViewModel : BaseViewModel<GuardianApprovalView>() {

    private val signature : MutableLiveData<SignatureModel> = MutableLiveData()

    fun setSignature(isFrom : String, bitmap : Bitmap) {
        signature.value = SignatureModel(isFrom, bitmap)
    }

    fun getSignature() : LiveData<SignatureModel> {
        return signature
    }
}