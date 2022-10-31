package com.ci.act.network

import com.ci.act.network.retrofit.API
import com.ci.act.network.retrofit.BaseRetrofit


class ApiHelper {

    fun getApi(): API {
        return BaseRetrofit.getMyRetrofit().create(API::class.java)
    }
}