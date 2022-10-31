package com.ci.act.data

import android.util.Log
import com.ci.act.BuildConfig
import com.ci.act.MyApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws


/**
 * Created by Venkatesh Vignesh on 3/18/2020.
 */
class LogInterceptor
    : Interceptor {
    private val TAG = "OkHttp"
    private val UTF8 = Charset.forName("UTF-8")
    private val logFile = MyApplication.getApplicationContext()


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val isDebug = BuildConfig.DEBUG
        val startTime = System.nanoTime()
        val request = chain.request()
        val requestBody = request.body
        val hasRequestBody = requestBody != null
        var responseBodyString: String?
        val requestHeaders = StringBuilder()
        val headers = request.headers
        //To get Headers
        var i = 0
        val count = headers.size
        while (i < count) {
            val name = headers.name(i)
            val value = headers.value(i)
            requestHeaders.append("\n${name} : ${value}")
            i++
        }
        val myRequestString: StringBuilder = StringBuilder()
        myRequestString.append("\nMethod: ${request.method},\nURL: ${request.url}\n")
        if (requestHeaders.isNotBlank()) {
            myRequestString.append("Headers: ${requestHeaders}")
        }
        if (hasRequestBody) {
            responseBodyString = bodyToString(request)
            myRequestString.append("\nBody: ${responseBodyString}")
        }
        if (isDebug) {
            Log.i(TAG, "\nApi Request -->  ${myRequestString} ")
        }
        val response: Response?
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            if (isDebug) {
                Log.i(TAG, "\nApi Request Failed --> ${e.message}")
            }
            throw e
        }
        val totalTimeTaken = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)
        val bufferedSource = response.body?.source()
        bufferedSource?.request(Long.MAX_VALUE)
        val buffer = bufferedSource?.buffer()
        val responseString = buffer?.clone()?.readString(UTF8).toString()
        val myResponseString: StringBuilder = StringBuilder()
//        ,Response Code :${response?.code()}
        myResponseString.append("\nURL: ${request.url},\nBody :${responseString}")
        if (isDebug) {
            Log.i(TAG, "Api Response --> ${myResponseString}")
            Log.i(TAG, "Api Time Taken --> ${totalTimeTaken} in nano")
        }
        return response
    }

    private fun bodyToString(request: Request): String? {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body?.writeTo(buffer)
            val body = buffer.readUtf8()
            val subtype = request.body?.contentType()?.subtype
            if (subtype?.contains("json") == true) {
                val obj = JSONObject(body)
                for (i in privateKeys()) {
                    if (obj.has(i)) {
                        if (i.equals("user", true)) {
                            val passkey = "password"
                            val user = obj.get(i)
                            if (user !== null && (user is JSONObject) && user.has(passkey)) {
                                user.remove(passkey)
                            }
                        } else {
                            obj.remove(i)
                        }
                    }
                }
                return obj.toString()
            }
            return body
        } catch (e: Exception) {
            return null
        }
    }

    private fun privateKeys(): ArrayList<String> {
        val privateKeys: ArrayList<String> = ArrayList()
        privateKeys.add("user")
        privateKeys.add("current_password")
        privateKeys.add("new_password")
        privateKeys.add("confirm_password")
        privateKeys.add("password")
        return privateKeys
    }

}