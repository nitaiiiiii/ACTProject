package com.ci.act.ui.livedata

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci.act.BR
import com.ci.act.MyApplication
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityLiveDataExampleBinding
import com.ci.act.ui.livedata.adapter.LiveDataAdapter
import com.ci.act.ui.livedata.model.LiveDataModel
import com.ci.act.util.showSnackBar

class LiveDataExampleActivity :
    BaseActivity<ActivityLiveDataExampleBinding, LiveDataExampleView, LiveDataExampleViewModel>(),
    LiveDataExampleView {
    private var liveDataAdapter: LiveDataAdapter? = null

    override fun getContentView(): Int = R.layout.activity_live_data_example

    override fun setViewModelClass(): Class<LiveDataExampleViewModel> =
        LiveDataExampleViewModel::class.java

    override fun getNavigator(): LiveDataExampleView = this

    override fun getBindingVariable(): Int = BR.liveDataViewModel

    override fun initViews(savedInstanceState: Bundle?) {
        setUpRecyclerView()
        addObservers()

    }

    private fun setUpRecyclerView() {
        liveDataAdapter = LiveDataAdapter()
        mViewDataBinding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@LiveDataExampleActivity)
            adapter = liveDataAdapter
        }
    }

    private fun addObservers() {
        MyApplication.getNetworkChangeListener().observe(this,
            {
                Log.e("Network", "${it.isConnected} type: ${it.connectedType}")
            })
        mViewModel?.myDataMutableLiveData?.observe(
            this,
            Observer<ArrayList<LiveDataModel>> { list ->
                liveDataAdapter?.submitList(list)
                showSnackBar("Items Added",this,true)
            })
    }
}