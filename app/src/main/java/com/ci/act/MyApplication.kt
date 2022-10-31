package com.ci.act

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.*
import com.ci.act.networktrigger.ConnectivityReceiver
import com.byji.main.networktrigger.model.ConnectivityModel
import com.ci.act.data.AppDataManager


/*
 * Created by Vignesh on 12-03-2020.
 */
class MyApplication : Application(), LifecycleObserver {
    init {
        myApplication = this
    }

    companion object {
        private lateinit var myApplication: Application
        private val networkChangeLiveData: MutableLiveData<ConnectivityModel> = MutableLiveData()

        fun getApplicationContext(): Context {
            return myApplication
        }
        fun getNetworkChangeListener():LiveData<ConnectivityModel>{
            return networkChangeLiveData
        }
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
        AppDataManager.getMyInstance().setApplicationOnStatus(true)
        registerNetworkReceiver()
    }

    /**
     * Shows background
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
        unRegisterNetworkReceiver()
        AppDataManager.getMyInstance().setApplicationOnStatus(false)
    }

    private fun registerNetworkReceiver() {
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED")
        registerReceiver(connectivityReceiver, filter)
    }
    private fun unRegisterNetworkReceiver() {
        unregisterReceiver(connectivityReceiver)
    }

    private val connectivityReceiver =
        ConnectivityReceiver(object : ConnectivityReceiver.ConnectivityReceiverListener {
            override fun onNetworkConnectionChanged(
                connectivityModel: ConnectivityModel
            ) {
                Log.e(
                    "NETWORK_CHANGE_INFO",
                    ": CONNECTED: ${connectivityModel.isConnected},  CONNECTED TYPE: ${connectivityModel.connectedType}"
                )
                networkChangeLiveData.value = connectivityModel
            }
        })

}