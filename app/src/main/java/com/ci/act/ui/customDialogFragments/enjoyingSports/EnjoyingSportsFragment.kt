package com.ci.act.ui.customDialogFragments.enjoyingSports

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentEnjoyingSportsBinding

class EnjoyingSportsFragment : BaseDialogFragmentNew<FragmentEnjoyingSportsBinding,EnjoyingSportsView,EnjoyingSportsViewModel>(),EnjoyingSportsView{
    override fun getContentView(): Int = R.layout.fragment_enjoying_sports

    override fun setViewModelClass(): Class<EnjoyingSportsViewModel> {
        return EnjoyingSportsViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.enjoyingSportsFragment

    override fun getNavigator(): EnjoyingSportsView = this

    override fun initViews(savedInstanceState: Bundle?) {
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