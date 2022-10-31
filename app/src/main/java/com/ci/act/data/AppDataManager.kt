package com.ci.act.data

import com.ci.act.network.ApiHelper
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.prefrence.PreferencesKeys
import com.google.gson.GsonBuilder


class AppDataManager private constructor() : DataManager {



    private val apiHelper: ApiHelper = ApiHelper()
    private val preferenceHelper: PreferenceHelper = PreferenceHelper.getInstance()

    private val gson = GsonBuilder().create()

    companion object {
        private var instance: AppDataManager? = null
        fun getMyInstance(): AppDataManager {
            if (instance == null) {
                instance = AppDataManager()
            }
            return instance!!
        }
    }

    override fun setFCMToken(token: String) {
        preferenceHelper.putString(PreferencesKeys.fcm_token, token)
    }

    override fun getFCMToken(): String {
        return preferenceHelper.getString(PreferencesKeys.fcm_token)
    }

    override fun setApplicationOnStatus(status: Boolean) {
        preferenceHelper.putBoolean(PreferencesKeys.application_status, status)
    }

    override fun getApplicationOnStatus(): Boolean {
        return preferenceHelper.getBoolean(PreferencesKeys.application_status)
    }
}