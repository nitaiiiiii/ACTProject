package com.ci.act.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ci.act.R

/********************************************************************************
 * @author       Tech.us Developers
 * @module       base
 * @name         BaseDialogFragment
 * @copyright    2020 - 2030 Tech.us Developers Inc
 * @createdon    10-08-2021.
 * @license      Tech.us Developers GPL - https://example.com/developer-license
 * @since        1.0
 *********************************************************************************/
abstract class BaseDialogFragmentNew<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> :
    DialogFragment(), BaseNavigator {

    var mViewDataBinding: T? = null
    var mViewModel: V? = null
 //   var mAppLocalization: AppLocalizationResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding =
            DataBindingUtil.inflate(inflater, getContentView(), container, false)
        mViewDataBinding?.lifecycleOwner = this
        this.mViewModel = ViewModelProvider(this)[setViewModelClass()]
        mViewModel?.navigator = getNavigator()
        return mViewDataBinding?.root!!
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.dialog_open_animation
        //mAppLocalization = mViewModel?.appDataManager?.getAppLocalization()
   //     organizationDetails = AppDataManager.getMyInstance().getOrganisationDetails()
        initViews(savedInstanceState)
    //    setViewText(mAppLocalization)
     //   setThemeColors(organizationDetails)
        addObservers()
        addObservables()
    }

    private fun addObservers() {

    }

    open fun startLoading() {
        if (activity is BaseActivity<*, *, *>) {
            (activity as? BaseActivity<*, *, *>)?.startLoading()
        } else {
            (parentFragment as? BaseFragment<*, *, *>)?.startLoading()
        }
    }


    open fun finishLoading() {
        if (activity is BaseActivity<*, *, *>) {
            (activity as? BaseActivity<*, *, *>)?.finishLoading()
        } else {
            (parentFragment as? BaseFragment<*, *, *>)?.finishLoading()
        }
    }


    abstract fun getContentView(): Int

    abstract fun setViewModelClass(): Class<V>

    abstract fun getBindingVariable(): Int

    abstract fun getNavigator(): N

    abstract fun initViews(savedInstanceState: Bundle?)

    /**
     * Set view text
     *
     * @param appLocalization
     */
//    abstract fun setViewText(appLocalization: AppLocalizationResponse?)
//
//    abstract fun setThemeColors(themes: OrganisationDetails?)

    abstract fun addObservables()

    override fun onStart() {
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        super.onStart()
    }

    override fun onResume() {
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        super.onResume()
    }

    fun getCommunicator(): BaseNavigator? {
        return when {
            activity is BaseNavigator -> {
                activity as BaseNavigator
            }
            parentFragment is BaseNavigator -> {
                parentFragment as BaseNavigator
            }
            else -> {
                null
            }
        }
    }


    fun showApiErrorMessage(
        icon: Int?,
        heading: String?,
        desc: String?,
        btnName: String?
    ) {
        mViewModel?.finishLoading()
        showErrorMessage(heading)
    }

    fun isNetworkConnected(): Boolean {
        return (activity as? BaseActivity<*, *, *>)?.isNetworkConnected() ?: false
    }

    fun hideSoftKeyboard() {
        try {
            val windowToken = dialog?.window?.decorView?.rootView ?: View(activity)
            val imm = dialog?.context
                ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                windowToken.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        } catch (ex: Exception) {
            Log.e("", "$ex")
        }
    }

}