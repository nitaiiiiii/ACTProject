package com.ci.act.prefrence


interface Prefs {
    fun setFCMToken(token: String)
    fun getFCMToken(): String
    fun setApplicationOnStatus(status: Boolean)
    fun getApplicationOnStatus(): Boolean

}