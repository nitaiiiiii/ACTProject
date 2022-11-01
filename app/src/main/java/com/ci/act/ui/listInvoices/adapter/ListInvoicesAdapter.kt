package com.ci.act.ui.listInvoices.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ci.act.R
import com.ci.act.ui.listInvoices.model.ListInvoicesModel

class ListInvoicesAdapter : RecyclerView.Adapter<ListInvoicesAdapter.ListInvoicesViewHolder>() {

    private val listInvoices: ArrayList<ListInvoicesModel.ListInvoicesModelItem> = ArrayList()

    class ListInvoicesViewHolder(listInvoicesView: View) :
        RecyclerView.ViewHolder(listInvoicesView) {
        var imgListInvoices: ImageView? = listInvoicesView.findViewById(R.id.imgListInvoices)
        var txtInvoicesListPremiumPlan: TextView? =
            listInvoicesView.findViewById(R.id.txtInvoicesListPremiumPlan)
        var txtInvoicesListPremiumDate: TextView? =
            listInvoicesView.findViewById(R.id.txtInvoicesListPremiumDate)
        var txtInvoicesListPremiumExpirationDate: TextView? =
            listInvoicesView.findViewById(R.id.txtInvoicesListPremiumExpirationDate)
        var txtInvoicesListPremiumDatePrice: TextView? =
            listInvoicesView.findViewById(R.id.txtInvoicesListPremiumDatePrice)
        var txtInvoicesListDateResult: TextView? =
            listInvoicesView.findViewById(R.id.txtInvoicesListDateResult)


        fun listInvoicesBindItems(listInvoicesModelItem: ListInvoicesModel.ListInvoicesModelItem) {
            txtInvoicesListPremiumPlan?.text = listInvoicesModelItem.planName
            txtInvoicesListPremiumDate?.text = listInvoicesModelItem.purchasedOn
            txtInvoicesListPremiumExpirationDate?.text = listInvoicesModelItem.expiredOn
            txtInvoicesListPremiumDatePrice?.text = listInvoicesModelItem.amount
            txtInvoicesListDateResult?.text = listInvoicesModelItem.paymentStatus

            val resId: Int = itemView.context.resources.getIdentifier(
                listInvoicesModelItem.image,
                "drawable",
                itemView.context.packageName
            )
            val profile = ResourcesCompat.getDrawable(itemView.resources, resId, null);

            imgListInvoices?.let {
                Glide.with(itemView.context)
                    .load(profile)
                    .error(R.drawable.ic_baseline_file_download_24)
                    .into(it)
            }

            if (listInvoicesModelItem.paymentStatus == "Success") {
                txtInvoicesListDateResult?.setTextColor(Color.GREEN)
            } else {
                txtInvoicesListDateResult?.setTextColor(Color.RED)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListInvoicesViewHolder {
        val listInvoices =
            LayoutInflater.from(parent.context).inflate(R.layout.new_list_invoices, parent, false)
        return ListInvoicesAdapter.ListInvoicesViewHolder(listInvoices)
    }

    override fun onBindViewHolder(holder: ListInvoicesViewHolder, position: Int) {
        holder.listInvoicesBindItems(listInvoices[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return listInvoices.size
    }

    fun addArray(array: ArrayList<ListInvoicesModel.ListInvoicesModelItem>) {
        listInvoices.clear()
        listInvoices.addAll(array)
        notifyDataSetChanged()
    }
}