package com.ci.act.ui.mysports.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ci.act.R
import com.ci.act.ui.differentSports.adapter.DifferentSportsAdapter
import com.ci.act.ui.differentSports.model.DifferentSportsModel
import com.ci.act.ui.mysports.model.MySportsModel

class MySportsAdapter : RecyclerView.Adapter<MySportsAdapter.MySportsViewHolder>() {
    private val arrayList: ArrayList<MySportsModel.MySportsModelItem> = ArrayList()
    lateinit var sportsClick: MySportsAdapter.SportsClick

    class MySportsViewHolder(mySportsView: View) : RecyclerView.ViewHolder(mySportsView) {

        var img: ImageView? = mySportsView.findViewById(R.id.imageViewSports)
        var img1: ImageView? = mySportsView.findViewById(R.id.imgCheckedCircle)
        var txt: TextView? = mySportsView.findViewById(R.id.txtDifferentActivities)

        fun mySportsBindItems(mySports: MySportsModel.MySportsModelItem) {

            txt?.text = mySports.sportsName
            val resId: Int = itemView.context.resources.getIdentifier(
                mySports.sportsimage,
                "drawable",
                itemView.context.packageName
            )
            val profile = ResourcesCompat.getDrawable(itemView.resources, resId, null);

            img?.let {
                Glide.with(itemView.context)
                    .load(profile)
                    .error(R.drawable.profile_picture)
                    .into(it)
            }

            if (mySports.isSelected) {
                img1?.visibility = View.VISIBLE
            } else {
                img1?.visibility = View.INVISIBLE
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySportsViewHolder {
        val mySports =
            LayoutInflater.from(parent.context).inflate(R.layout.sports_items, parent, false)
        return MySportsViewHolder(mySports)
    }

    override fun onBindViewHolder(holder: MySportsViewHolder, position: Int) {
        holder.mySportsBindItems(arrayList[holder.adapterPosition])

        holder.img?.setOnClickListener {
            sportsClick.sportsClick(arrayList[holder.adapterPosition], holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addArray(array: ArrayList<MySportsModel.MySportsModelItem>) {
        arrayList.clear()
        arrayList.addAll(array)
        notifyDataSetChanged()
    }

    fun updateList(array: MySportsModel.MySportsModelItem, position: Int) {
        arrayList.set(position, array)
        notifyItemChanged(position)
    }

    fun sportsClick(sportsClick: SportsClick) {
        this.sportsClick = sportsClick
    }

    interface SportsClick {
        fun sportsClick(sports: MySportsModel.MySportsModelItem, position: Int)
    }
}