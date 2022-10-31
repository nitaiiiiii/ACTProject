package com.ci.act.ui.home.faqList.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.home.faqList.model.FaqListModel

class FaqListAdapter : RecyclerView.Adapter<FaqListAdapter.FaqListViewHolder>() {

    private val faqList: ArrayList<FaqListModel.FaqListModelItem> = ArrayList()

    var check: Boolean = false

    inner class FaqListViewHolder(faqView: View) : RecyclerView.ViewHolder(faqView) {
        var txtPaymentQuestionsInformation: TextView? =
            faqView.findViewById(R.id.txtPaymentQuestionsInformation)
        var imgAdding: ImageView? = faqView.findViewById(R.id.imgAdding)
        var txtInformationAnswers: TextView? = faqView.findViewById(R.id.txtInformationAnswers)
        var imgClosing: ImageView? = faqView.findViewById(R.id.imgClosing)

        fun faqListBindItems(faqListModelItem: FaqListModel.FaqListModelItem) {
            txtPaymentQuestionsInformation?.text = faqListModelItem.questionName
            txtInformationAnswers?.text = faqListModelItem.answer

            txtPaymentQuestionsInformation?.setOnClickListener {
                if (check) {
                    txtInformationAnswers?.visibility = View.GONE
                    imgClosing?.visibility = View.GONE
                    imgAdding?.visibility = View.VISIBLE
                    txtPaymentQuestionsInformation?.setTextColor(Color.BLACK)
                    check = false
                } else {
                    txtInformationAnswers?.visibility = View.VISIBLE
                    imgClosing?.visibility = View.VISIBLE
                    imgAdding?.visibility = View.GONE
                    txtPaymentQuestionsInformation?.setTextColor(Color.LTGRAY)
                    check = true
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqListViewHolder {
        val liveFAQs =
            LayoutInflater.from(parent.context).inflate(R.layout.inflate_faqs_list, parent, false)
        return FaqListViewHolder(liveFAQs)
    }

    override fun onBindViewHolder(holder: FaqListViewHolder, position: Int) {
        holder.faqListBindItems(faqList[holder.adapterPosition])

    }

    override fun getItemCount(): Int {
        return faqList.size
    }

    fun addArray(array: ArrayList<FaqListModel.FaqListModelItem>) {
        faqList.clear()
        faqList.addAll(array)
        notifyDataSetChanged()
    }
}