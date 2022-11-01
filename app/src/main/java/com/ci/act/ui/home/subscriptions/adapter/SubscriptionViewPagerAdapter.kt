package com.ci.act.ui.home.subscriptions.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.home.subscriptions.model.SubscriptionModel

class SubscriptionViewPagerAdapter :
    RecyclerView.Adapter<SubscriptionViewPagerAdapter.SubscriptionViewHolder>() {

    val subscriptionArray = ArrayList<SubscriptionModel>()
    private lateinit var subscriptionClick: SubscriptionClick

    inner class SubscriptionViewHolder(subscriptionView: View) :
        RecyclerView.ViewHolder(subscriptionView) {
        var premiumSubscription: ConstraintLayout? =
            subscriptionView.findViewById(R.id.premiumSubscription)
        var platinumSubscription: ConstraintLayout? =
            subscriptionView.findViewById(R.id.platinumSubscription)
        var btnSignatureBox1: Button? = subscriptionView.findViewById(R.id.btnSignatureBox1)
        var btnSignatureBox: Button? = subscriptionView.findViewById(R.id.btnSignatureBox)


        fun subscriptionBindItems(subscriptionModel: SubscriptionModel) {
            if (subscriptionModel.subscriptionType == "Premium") {
                premiumSubscription?.visibility = View.VISIBLE
                platinumSubscription?.visibility = View.GONE
            } else {
                premiumSubscription?.visibility = View.GONE
                platinumSubscription?.visibility = View.VISIBLE
            }

            btnSignatureBox1?.setOnClickListener {
                subscriptionClick.subscriptionClick(subscriptionModel)
            }
            btnSignatureBox?.setOnClickListener {
                subscriptionClick.subscriptionClick(subscriptionModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val subscription = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_subscription, parent, false)
        return SubscriptionViewHolder(subscription)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        holder.subscriptionBindItems(subscriptionArray[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return subscriptionArray.size
    }

    fun addArrayList(list: ArrayList<SubscriptionModel>) {
        subscriptionArray.clear()
        subscriptionArray.addAll(list)
        notifyDataSetChanged()
    }

    fun subscriptionClick(subscriptionClick: SubscriptionClick) {
        this.subscriptionClick = subscriptionClick
    }

    interface SubscriptionClick {
        fun subscriptionClick(subscription: SubscriptionModel)
    }

}