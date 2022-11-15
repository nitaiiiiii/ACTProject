package com.ci.act.ui.home.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ci.act.base.BaseViewModel

class NotificationsViewModel : BaseViewModel<NotificationsView>(){

    private val isSelectClicked : MutableLiveData<Boolean> = MutableLiveData()

    fun setSelect(isSelectClicked : Boolean) {
        this.isSelectClicked.value = isSelectClicked
    }

    fun getSelect() : LiveData<Boolean> {
        return isSelectClicked
    }
}