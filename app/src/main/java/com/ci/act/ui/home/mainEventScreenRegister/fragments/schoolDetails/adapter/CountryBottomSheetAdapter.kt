package com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.databinding.InflateBottomsheetSelectionBinding
import com.ci.act.ui.home.mainEventScreenRegister.fragments.schoolDetails.model.CountryModel

class CountryBottomSheetAdapter : RecyclerView.Adapter<CountryBottomSheetAdapter.CustomSpinnerDialogViewHolder>() {
    private var bottomSheetArray: ArrayList<CountryModel.CountryModelItem> = ArrayList()
    private var selectedText: String? = null

    inner class CustomSpinnerDialogViewHolder(val binding: InflateBottomsheetSelectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bottomSheetArray: CountryModel.CountryModelItem) {
            binding.txtSelectCountryName.text = bottomSheetArray.countryName
            binding.txtSelectFlag.visibility = View.GONE
            binding.rootLayoutSpinner.setBackgroundColor(ContextCompat.getColor(itemView.context,
                    if (selectedText != null && bottomSheetArray.countryName == selectedText) { R.color.light_grey } else { R.color.white }))

            itemView.setOnClickListener {
                onClick?.onClick(bottomSheetArray)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomSpinnerDialogViewHolder {
        return CustomSpinnerDialogViewHolder(InflateBottomsheetSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomSpinnerDialogViewHolder, position: Int) {
        holder.bind(bottomSheetArray[holder.absoluteAdapterPosition])
        if (position == itemCount - 1) {
            holder.binding.viewSelectionBottom.visibility = View.INVISIBLE
        } else {
            holder.binding.viewSelectionBottom.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return bottomSheetArray.size
    }

    fun setOnClickListener(onClick: OnClick) {
        this.onClick = onClick
    }

    private var onClick: OnClick? = null

    interface OnClick {
        fun onClick(bottomSheetArray: CountryModel.CountryModelItem)
    }

    fun addList(bottomSheetArray: ArrayList<CountryModel.CountryModelItem>, selectedText: String?) {
        this.selectedText = selectedText
        this.bottomSheetArray.clear()
        this.bottomSheetArray.addAll(bottomSheetArray)
        notifyDataSetChanged()
    }
}