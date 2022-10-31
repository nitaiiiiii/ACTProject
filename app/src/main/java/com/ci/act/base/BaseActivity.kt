package com.ci.act.base

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ci.act.R
import com.ci.act.util.showCurrentStatusBarColor
import com.ci.act.util.showSnackBar
import kotlinx.android.synthetic.main.inflate_api_dialog.*


abstract class BaseActivity<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> :
    AppCompatActivity(),
    BaseNavigator {

    private var dialog: Dialog? = null
    var mViewDataBinding: T? = null
    var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initViews(savedInstanceState)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getContentView())
        mViewDataBinding?.lifecycleOwner = this
        this.mViewModel = ViewModelProvider(this)[setViewModelClass()]
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
        mViewModel?.navigator = getNavigator()
    }

    /**
     * Return layout id for each screen
     */
    abstract fun getContentView(): Int

    /**
     * Assign view model for appropriate screen
     */

    abstract fun setViewModelClass():Class<V>


    /**
     * Assign navigator for appropriate screen
     */
    abstract fun getNavigator(): N


    /**
     * Assign binding variable from BR
     */
    abstract fun getBindingVariable(): Int

    abstract fun initViews(savedInstanceState: Bundle?)


    private fun initializeLoadingDialog() {
        if (dialog != null) {
            return
        }
        dialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.custom_loading_gif)
            setCancelable(false)
            setCanceledOnTouchOutside(false)

            val lp = window!!.attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            window?.attributes = lp
            window?.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this@BaseActivity,
                    R.drawable.gradient_for_progress_bar_bg
                )
            )
        }
    }

    override fun noInternetError() {
        finishLoading()
        showMessage(getString(R.string.no_internet_connection))
    }

    override fun onUnknownError(error: String?) {
        finishLoading()
        showMessage(error)
    }

    fun showMessage(message: String?) {
        message?.let {
            showSnackBar(it, this, R.color.red)
        }
    }

    override fun showSuccessMessage(message: String?){
        message?.let {
            showSnackBar(it, this, R.color.green)
        }
    }

    override fun showApiErrorMessage(message: String?) {
        message?.let {
            showSnackBar(it, this, R.color.red)
        }
    }

    override fun showErrorMessage(message: String?){
        message?.let {
            showSnackBar(it, this, R.color.ruby_red)
        }
    }
    override fun showErrorMessage(icon: Int?, heading: String?, desc: String?, btnName: String?) {
        showApiErrorMessage(heading)
    }

    override fun onTimeout() {
        finishLoading()
        showMessage(getString(R.string.time_out_message))

    }

    override fun onNetworkError() {
        finishLoading()
        showMessage(getString(R.string.no_internet_connection))
    }

    override fun onApiFailed(message: String?) {
        finishLoading()
        showMessage(message)
    }




    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        var handled = false
        for (f in fragmentList) {
            if (f is BaseFragment<*, *, *>) {
                handled = f.onBackPressed()

                if (handled) {
                    break
                }
            }
        }

        if (!handled) {
            super.onBackPressed()
        }
    }

    fun showOrHideStatusBar(isStatusBarVisible: Boolean) {
        window.apply {
            if (isStatusBarVisible) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    window.insetsController?.hide(WindowInsets.Type.statusBars())
                }
            } else {
                setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showCurrentStatusBarColor(this)
    }

    fun isNetworkConnected() =
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

    override fun onForceLogOut(message: String?) {
        showApiDialog(message ?: "")
    }

    override fun showApiDialog(message: String) {
        finishLoading()
        with(Dialog(this)) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.inflate_api_dialog)
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            tvErrorMessage.text = message
            btnOk.setOnClickListener {
                dismiss()
            }
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
////        getViewModel()?.appDataManager?.clearProfileData()
    }

    private fun addObservers() {
        mViewModel?.mLoadingStatus?.observe(this, Observer { isLoading ->
            if (isLoading) {
                startLoading()
            } else {
                finishLoading()
            }
        })
    }

    /*
        This is used to show global progress bar
        Note: Don't use inside activity class. we need to call from viewModel(i.e.,  mViewModel?.startLoading() )
     */
    open fun startLoading() {
        initializeLoadingDialog()
        if (dialog?.isShowing != true) {
            dialog?.show()
        }
    }

    /*
         This is used to hide global progress bar
         Note: Don't use inside activity class. we need to call from viewModel(i.e.,  mViewModel?.finishLoading() )
        */
    open fun finishLoading() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

    override fun onDestroy() {
        finishLoading()
        mViewModel?.clearCalls()
        super.onDestroy()
    }



}