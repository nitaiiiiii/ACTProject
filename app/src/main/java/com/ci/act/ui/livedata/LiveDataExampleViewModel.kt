package com.ci.act.ui.livedata

import androidx.lifecycle.MutableLiveData
import com.ci.act.base.BaseViewModel
import com.ci.act.ui.livedata.model.LiveDataModel

class LiveDataExampleViewModel:BaseViewModel<LiveDataExampleView>() {
    val myDataMutableLiveData:MutableLiveData<ArrayList<LiveDataModel>> = MutableLiveData()

   fun  addMoreItems(){
       updateList(myDataMutableLiveData.value?.size?:0)
   }

    private fun updateList(from:Int){
        val list = ArrayList<LiveDataModel>()
        myDataMutableLiveData.value?.let {
            list.addAll(it)
        }
        for (i in from..(from+20)){
            list.add(LiveDataModel(("Name $i")))
        }
        myDataMutableLiveData.value = list
    }


}