package com.ci.act.util

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/********************************************************************************
 * @author       Tech.us Developers
 * @module       helper
 * @name         crypto-handler
 * @description  Encryption, Decryption Functions Handler
 * @copyright    2020 - 2030 Tech.us Developers Inc
 * @createdon    05-12-2020.
 * @license      Tech.us Developers GPL - https://example.com/developer-license
 * @since        1.0
 *********************************************************************************/
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val TAG = "SingleLiveEvent"
    private val mPending = AtomicBoolean(false)


    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        super.observe(owner, Observer<T> { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        setValue(null)
    }
}