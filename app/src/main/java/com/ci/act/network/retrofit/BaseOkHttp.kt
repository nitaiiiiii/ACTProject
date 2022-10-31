package com.ci.act.network.retrofit

import android.util.Log
import com.ci.act.BuildConfig
import com.ci.act.data.AppDataManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Buffer
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


open class BaseOkHttp {
    private val NETWORK_CONNECTION_TIMEOUT = 60L
    private var appDataManager: AppDataManager? = null

    init {
        appDataManager = AppDataManager.getMyInstance()
    }

    /**
     * Provide OkHttpClient object with header
     */
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(provideBasicAuthInterceptor())
            .connectTimeout(NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Apply header to OkHttpClient chain request
     */
    private fun provideBasicAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val uTF8 = Charset.forName("UTF-8")!!
            val request = chain.request().newBuilder()
            request.addHeader("Content-Type", "application/json")
//            val userAccessToken = appDataManager?.getAuthorization()!!
            val userAccessToken = ""
            if (userAccessToken.isEmpty() || checkForNoBearer(chain.request().url.toString())) {
                request.addHeader("authorization", BuildConfig.api_key)
            } else {
                request.addHeader("authorization", "bearer $userAccessToken")
            }
            val requestBuilder = request.build()
            printLog("url==>: ${requestBuilder.url}")
            printLog("headers==>: ${requestBuilder.headers}")
            printLog("Body==>: ${bodyToString(requestBuilder)}")

            val response = chain.proceed(requestBuilder)
            val bufferedSource = response.body?.source()
            bufferedSource?.request(Long.MAX_VALUE)
            val buffer = bufferedSource?.buffer()
            printLog("Response==> ${buffer?.clone()?.readString(uTF8).toString()}")
            response
        }
    }

    private fun printLog(message: String?) {
        if (BuildConfig.DEBUG) {
            message?.let {
                Log.v("BaseOkHttp", it)
            }
        }
    }

    private val noBearerApis: ArrayList<String> = ArrayList()

    init {
        with(noBearerApis) {
            add("app_details")
            add("email_login")
            add("forgot_password")
        }
    }

    private fun checkForNoBearer(url: String): Boolean {
        for (api in noBearerApis) {
            if (url.contains("${BuildConfig.base_url}$api")) {
                return true
            }
        }
        return false
    }



    private fun bodyToString(request: Request): String? {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body?.writeTo(buffer)
            val body = buffer.readUtf8()
            //            val subtype = request.body()?.contentType()?.subtype()
            //            if (subtype?.contains("json") == true) {
            //                val obj = JSONObject(body)
            //                return obj.toString()
            //            }
            return body
        } catch (e: Exception) {
            return null
        }
    }
}