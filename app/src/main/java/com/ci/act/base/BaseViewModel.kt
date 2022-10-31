package com.ci.act.base



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ci.act.callbacks.model.ErrorHandlingModel
import com.ci.act.data.AppDataManager
import retrofit2.Call


open class BaseViewModel<N : BaseNavigator>(val appDataManager: AppDataManager = AppDataManager.getMyInstance()) :
    ViewModel() {

    var navigator: N? = null
    private val callList: ArrayList<Call<*>> = ArrayList()
    private val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    private val forcedLogoutDialog: MutableLiveData<String?> = MutableLiveData()
    private val errorLayout: MutableLiveData<ErrorHandlingModel?> = MutableLiveData()

    val mForcedLogoutDialog: LiveData<String?>
        get() = forcedLogoutDialog

    val mLoadingStatus: LiveData<Boolean>
        get() = loadingStatus


    fun clearCalls() {
        for (callItem in callList) {
            callItem.cancel()
        }
        callList.clear()
    }

    fun addCall(call: Call<*>) {
        callList.add(call)
    }

    fun startLoading(){
        loadingStatus.value = true
    }

    fun finishLoading(){
        loadingStatus.value = false
    }
}