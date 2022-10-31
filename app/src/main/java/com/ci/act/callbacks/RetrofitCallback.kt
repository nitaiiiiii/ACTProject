package com.ci.act.callbacks

import com.ci.act.base.BaseNavigator
import com.ci.act.data.AppDataManager
import com.ci.act.data.BaseResponse
import com.ci.act.data.EncryptedResponse
import com.ci.act.util.secure.AES
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class RetrofitCallback<T : EncryptedResponse>(
    private val mCallback: MyCallback<T>,
    private val navigator: BaseNavigator,
    private val myClass: Class<T>) : Callback<T> {
    private val appDataManager = AppDataManager.getMyInstance()
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (call.isCanceled) {
            return
        }
        if (response.isSuccessful) {
            try {
                val encryptedResponse: EncryptedResponse? = response.body()
                val appDetailsEncrypt = AES.decrypt(encryptedResponse?.data ?: "")
                val responseModel: T = Gson().fromJson(appDetailsEncrypt, myClass) as T
                val baseResponse: BaseResponse = responseModel as BaseResponse
                if (baseResponse.valid) {
                    mCallback.onResponse(call, responseModel)
                } else {
                    if (baseResponse.forceLogout == 1) {
                        navigator.showApiDialog(baseResponse.message ?: "Your account has been logged in another device")
                    }
//                    else if (baseResponse.isExpired == 1) {
//                        Log.v("OkHttp:","app/v1/NewRefreshToken called")
//                        val myEncryptedRequest =
//                            AES.toJsonEncryptedString(GenerateAccessTokenRequest())
//                        appDataManager.getNewRefreshToken(appDataManager.getRefreshAuthorization(), GlobalRequestModel(myEncryptedRequest))
//                            .subscribeWith(object : DisposableObserver<EncryptedResponse>() {
//                                override fun onComplete() {
//
//                                }
//
//                                override fun onNext(response: EncryptedResponse) {
//                                    val eData = AES.decrypt(response.data ?: "")
//                                    val generateAccessTokenModel: GenerateAccessTokenModel? = Gson().fromJson(eData, GenerateAccessTokenModel::class.java)
//                                    Log.v("OkHttp:","app/v1/NewRefreshToken called main Api")
//                                    if (generateAccessTokenModel?.token?.trim()?.isNotEmpty() == true) {
//                                        appDataManager.setAuthorization(generateAccessTokenModel.token)
//                                        call.clone().enqueue(this@RetrofitCallback)
//                                    } else {
//                                        navigator.showApiDialog(generateAccessTokenModel?.message ?: "Authentication failed")
//                                    }
//                                }
//
//                                override fun onError(e: Throwable) {
//                                    when (e) {
//                                        is HttpException -> navigator.onNetworkError()
//                                        is SocketTimeoutException -> navigator.onTimeout()
//                                        is IOException -> navigator.onNetworkError()
//                                        else -> navigator.onUnknownError(e.message)
//                                    }
//                                }
//                            })
//                    }
                    else if (baseResponse.isAccountDeleted == 1) {
                        navigator.showApiDialog(baseResponse.message ?: "Your account has been deleted. Please contact admin")
                    } else if (baseResponse.isEmailNotRegistered == 1) {
                        navigator.showApiErrorMessage(baseResponse.message ?: "The email address you entered is not registered")
                    } else if (baseResponse.isAccountDisabled == 1) {
                        navigator.showApiDialog(baseResponse.message ?: "Your account has been disabled by the administrator")
                    } else {
                        navigator.onApiFailed(baseResponse.message)
                    }
                }
            } catch (e: Exception) {
                navigator.onUnknownError(e.message)
            }
        } else {
            navigator.onApiFailed(response.message())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (call.isCanceled) {
            return
        }
        when (t) {
            is HttpException -> navigator.onNetworkError()
            is SocketTimeoutException -> navigator.onTimeout()
            is IOException -> navigator.onNetworkError()
            else -> navigator.onUnknownError(t.message)
        }
    }

    interface MyCallback<V> {
        fun onResponse(call: Call<V>, response: V)
    }
}
