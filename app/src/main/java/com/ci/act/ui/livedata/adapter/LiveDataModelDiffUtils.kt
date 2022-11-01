package com.ci.act.ui.livedata.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ci.act.ui.livedata.model.LiveDataModel

class LiveDataModelDiffUtils : DiffUtil.ItemCallback<LiveDataModel>() {
    override fun areItemsTheSame(oldItem: LiveDataModel, newItem: LiveDataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LiveDataModel, newItem: LiveDataModel): Boolean {
        return oldItem.equals(newItem)
    }
}