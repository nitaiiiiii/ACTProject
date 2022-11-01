package com.ci.act.ui.customDialogFragments.feedback

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentFeedbackBinding

class FeedbackFragment :
    BaseDialogFragmentNew<FragmentFeedbackBinding, FeedBackView, FeedbackViewModel>(),
    FeedBackView {
    override fun getContentView(): Int = R.layout.fragment_feedback

    override fun setViewModelClass(): Class<FeedbackViewModel> {
        return FeedbackViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.feedbackFragment

    override fun getNavigator(): FeedBackView = this

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