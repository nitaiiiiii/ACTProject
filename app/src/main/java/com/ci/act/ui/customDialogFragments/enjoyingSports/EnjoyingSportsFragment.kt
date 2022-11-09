package com.ci.act.ui.customDialogFragments.enjoyingSports

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseDialogFragmentNew
import com.ci.act.databinding.FragmentEnjoyingSportsBinding
import com.ci.act.ui.authentication.signin.SignInActivity
import com.ci.act.ui.home.liveEvents.LiveEventsActivity

class EnjoyingSportsFragment :
    BaseDialogFragmentNew<FragmentEnjoyingSportsBinding, EnjoyingSportsView, EnjoyingSportsViewModel>(),
    EnjoyingSportsView {
    override fun getContentView(): Int = R.layout.fragment_enjoying_sports

    override fun setViewModelClass(): Class<EnjoyingSportsViewModel> {
        return EnjoyingSportsViewModel::class.java
    }

    override fun getBindingVariable(): Int = BR.enjoyingSportsFragment

    override fun getNavigator(): EnjoyingSportsView = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.MyDialogPopup)

        isCancelable = false
    }

    override fun initViews(savedInstanceState: Bundle?) {

        mViewDataBinding?.tvClose?.setOnClickListener {
            LiveEventsActivity.isShowingEnjoyingSportsDialog = false
            dialog?.dismiss()
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {

        mViewDataBinding?.ratingBar?.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                if (p1 != 0F) {
                    mViewDataBinding?.btnWriteReview?.visibility = View.VISIBLE
                    mViewDataBinding?.tvFeedbackTitle?.text = "THANKS FOR YOUR FEEDBACK"
                    mViewDataBinding?.tvTagLineFeedback?.text = "You can also write a review."
                    mViewDataBinding?.tvClose?.text = "Close"
                } else {
                    mViewDataBinding?.btnWriteReview?.visibility = View.GONE
                    mViewDataBinding?.tvFeedbackTitle?.text = "ENJOYING ATHLETAFIED?"
                    mViewDataBinding?.tvTagLineFeedback?.text = "Tap a star to rate it on  the\nPlay Store."
                    mViewDataBinding?.tvClose?.text = "Not Now"
                }
            }
        }
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