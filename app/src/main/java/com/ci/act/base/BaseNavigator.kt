package com.ci.act.base


interface BaseNavigator {


    fun noInternetError()

    fun onUnknownError(error: String?)

    fun onTimeout()

    fun onNetworkError()

    fun showApiDialog(message: String)

    fun showSuccessMessage(message: String?)

    fun showErrorMessage(message: String?)

    fun showApiErrorMessage(message: String?)

    fun showErrorMessage(
        icon: Int? = null,
        heading: String? = null,
        desc: String? = null,
        btnName: String? = null
    )

    fun onApiFailed(message: String?)

    fun onForceLogOut(message: String?)


}