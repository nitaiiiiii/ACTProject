package com.ci.act.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ci.act.R


abstract class BaseFragment<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> :
    Fragment(), BaseNavigator {

    var mViewDataBinding: T? = null
    var mViewModel: V? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getContentView(), container, false)
        this.mViewModel = if (mViewModel == null) setViewModel() else mViewModel
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewModel?.navigator = getNavigator()
        return mViewDataBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(savedInstanceState)
        addObservers()
        addObservables()
    }


    abstract fun getContentView(): Int

    abstract fun setViewModel(): V?

    abstract fun getBindingVariable(): Int

    abstract fun getNavigator(): N

    abstract fun initViews(savedInstanceState: Bundle?)


    override fun onUnknownError(error: String?) {
        finishLoading()
        showErrorMessage(error)
    }

    override fun onTimeout() {
        finishLoading()
        showErrorMessage(getString(R.string.time_out_message))
    }

    override fun onNetworkError() {
        noInternetError()
    }


    override fun noInternetError() {
        finishLoading()
        showErrorMessage(getString(R.string.no_internet_connection))
    }

    private fun addObservers() {
        mViewModel?.mLoadingStatus?.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                startLoading()
            } else {
                finishLoading()
            }
        })
    }

    fun startLoading() {
        (activity as? BaseActivity<*, *, *>)?.startLoading()
    }

    fun finishLoading() {
        (activity as? BaseActivity<*, *, *>)?.finishLoading()
    }


    /**
     * Could handle back press.
     * @return true if back press was handled
     */
    open fun onBackPressed(): Boolean {
        finishLoading()
        return false
    }


    override fun onForceLogOut(message: String?) {
        finishLoading()
        (activity as? BaseActivity<*, *, *>)?.onForceLogOut(message)
    }

    override fun showApiDialog(message: String) {
        finishLoading()
        (activity as? BaseActivity<*, *, *>)?.showApiDialog(message)
    }

    override fun showSuccessMessage(message: String?) {
        (activity as? BaseActivity<*, *, *>)?.showSuccessMessage(message)


    }

    override fun showErrorMessage(message: String?) {
        (activity as? BaseActivity<*, *, *>)?.showErrorMessage(message)

    }

    override fun showApiErrorMessage(message: String?) {
        (activity as? BaseActivity<*, *, *>)?.showApiErrorMessage(message)

    }

    override fun showErrorMessage(icon: Int?, heading: String?, desc: String?, btnName: String?) {
        showApiErrorMessage(heading)
    }


    override fun onApiFailed(message: String?) {
        finishLoading()
        showErrorMessage(message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel?.clearCalls()
    }

    override fun onDetach() {
        mViewModel?.clearCalls()
        super.onDetach()
    }

    override fun onDestroy() {
        mViewModel?.clearCalls()
        super.onDestroy()
    }

    abstract fun addObservables()
}