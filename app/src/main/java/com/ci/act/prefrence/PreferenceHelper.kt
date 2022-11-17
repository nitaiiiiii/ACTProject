package com.ci.act.prefrence

import android.content.Context
import android.content.SharedPreferences
import com.ci.act.BuildConfig
import com.ci.act.MyApplication
import com.ci.act.extensions.edit


class PreferenceHelper private constructor() {

    private var userPrefs: SharedPreferences? = null
    private var appPrefs: SharedPreferences? = null
    private var userDetails: SharedPreferences? = null
    private var sharedPreferences: SharedPreferences = MyApplication.getApplicationContext().getSharedPreferences(PreferencesKeys.act, Context.MODE_PRIVATE)


    init {

        userPrefs = MyApplication.getApplicationContext()
            .getSharedPreferences(BuildConfig.preference_name, Context.MODE_PRIVATE)
        userDetails = MyApplication.getApplicationContext()
            .getSharedPreferences(BuildConfig.preference_name+"UserDetails", Context.MODE_PRIVATE)
        appPrefs =
            MyApplication.getApplicationContext().getSharedPreferences(
                BuildConfig.preference_name.plus("Permanent"),
                Context.MODE_PRIVATE
            )
    }

    companion object {
        private var preferenceHelper: PreferenceHelper? = null

        fun getInstance(): PreferenceHelper {
            if (preferenceHelper == null) {
                preferenceHelper = PreferenceHelper()
            }
            return preferenceHelper!!
        }
    }

    fun isPushNotificationFinished() : Boolean {
        return sharedPreferences.getBoolean(PreferencesKeys.pushNotifications.plus("Permanent"),  false)
    }

    fun finishPushNotification() {
        val edit = sharedPreferences.edit()
        edit.putBoolean(PreferencesKeys.pushNotifications.plus("Permanent"), true)
        edit.apply()
    }


    fun isOnBoardingFinished(): Boolean? {
        return userPrefs?.getBoolean(PreferencesKeys.isOnBoardingFinished, false)
    }

    fun isChooseSportsFinished() : Boolean? {
        return userPrefs?.getBoolean(PreferencesKeys.isChooseSportsFinished,  false)
    }

    fun finishedChooseSports() {
        val edit = userPrefs?.edit()
        edit?.putBoolean(PreferencesKeys.isChooseSportsFinished, true)
        edit?.apply()
    }

    fun finishOnBoarding() {
        val edit = userPrefs?.edit()
        edit?.putBoolean(PreferencesKeys.isOnBoardingFinished, false)
        edit?.apply()
    }

    fun putString(key: String, value: String) {
        appPrefs?.edit {
            putString(key, value)
        }
    }

    fun putInt(key: String, value: Int) {
        appPrefs?.edit {
            putInt(key, value)
        }
    }

    fun putBoolean(key: String, value: Boolean) {
        appPrefs?.edit {
            putBoolean(key, value)
        }
    }

    fun putFloat(key: String, value: Float) {
        appPrefs?.edit {
            putFloat(key, value)
        }
    }

    fun putTime(key: String, value: Long) {
        appPrefs?.edit {
            putLong(key, value)
        }
    }

    fun getTime(key: String): Long {
        return appPrefs?.getLong(key, 0)!!
    }

    fun getString(key: String): String {
        return appPrefs?.getString(key, "")!!
    }

    fun getInt(key: String): Int {
        return appPrefs?.getInt(key, 0)!!
    }

    fun getBoolean(key: String): Boolean {
        return appPrefs?.getBoolean(key, false)!!
    }

    fun getFloat(key: String): Float {
        return appPrefs?.getFloat(key, 0f)!!
    }

    fun clearPreference(key: String) {
        if (appPrefs?.contains(key) == true) {
            appPrefs?.edit {
                remove(key)
            }
        }
    }

    fun clearAppPrefs() {
        appPrefs?.edit {
            clear()
        }
    }

    fun putUserPrefString(key: String, value: String) {

        userPrefs?.edit {
            putString(key, value)
        }
    }

    fun getUserPrefString(key: String) {
        userPrefs?.getString(key, "")
    }


    fun clearUserPrefs() {
        userPrefs?.edit {
            clear()
        }
    }

    fun setUserDetails(value: String) {
        userDetails?.edit {
            putString(PreferencesKeys.userLoginDetails, value)
        }
    }

    fun getUserDetails() : String? {
        return userDetails?.getString(PreferencesKeys.userLoginDetails, "")
    }


    fun clearUserDetails() {
        userDetails?.edit {
            clear()
        }
    }


}