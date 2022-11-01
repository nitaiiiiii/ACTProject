package com.ci.act.ui.livedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.livedata.model.LiveDataModel
import kotlinx.android.synthetic.main.inflate_live_data_item.view.*

class LiveDataAdapter :
    ListAdapter<LiveDataModel, LiveDataAdapter.LiveDataViewHolder>(LiveDataModelDiffUtils()) {

    inner class LiveDataViewHolder(private val myItemView: View) :
        RecyclerView.ViewHolder(myItemView) {
        fun onBind(item: LiveDataModel?) {
            item?.let {
                myItemView.txtName.text = it.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_live_data_item, parent, false)
        return LiveDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: LiveDataViewHolder, position: Int) {
        holder.onBind(getItem(holder.adapterPosition))
    }


}