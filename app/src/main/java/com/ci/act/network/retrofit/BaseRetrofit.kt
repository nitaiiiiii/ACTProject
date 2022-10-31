package com.ci.act.network.retrofit

import com.ci.act.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object BaseRetrofit : BaseOkHttp() {

    private var retrofit: Retrofit? = null

    init {
        createRetrofit()
    }

    fun getMyRetrofit(): Retrofit {
        if (retrofit == null) {
            createRetrofit()
        }
        return retrofit!!
    }


    private fun createRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .client(provideOKHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}