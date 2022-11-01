package com.ci.act.ui.differentSports.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ci.act.R
import com.ci.act.ui.differentSports.model.DifferentSportsModel

class DifferentSportsAdapter :
    RecyclerView.Adapter<DifferentSportsAdapter.DifferentSportsViewHolder>() {
    private val arrayList: ArrayList<DifferentSportsModel.DifferentSportsModelItem> = ArrayList()
    lateinit var sportsClick: SportsClick

    inner class DifferentSportsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var img: ImageView? = view.findViewById(R.id.imageViewSports)
        var img1: ImageView? = view.findViewById(R.id.imgCheckedCircle)
        var txt: TextView? = view.findViewById(R.id.txtDifferentActivities)

        fun differentSportsBindItems(differentSports: DifferentSportsModel.DifferentSportsModelItem) {

            txt?.text = differentSports.sportsName
            val resId: Int = itemView.context.resources.getIdentifier(
                differentSports.sportsimage,
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

            if (differentSports.isSelected) {
                img1?.visibility = View.VISIBLE
            } else {
                img1?.visibility = View.INVISIBLE
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DifferentSportsViewHolder {
        val differentSports =
            LayoutInflater.from(parent.context).inflate(R.layout.sports_items, parent, false)
        return DifferentSportsViewHolder(differentSports)
    }

    override fun onBindViewHolder(holder: DifferentSportsViewHolder, position: Int) {
        holder.differentSportsBindItems(arrayList[holder.adapterPosition])

        holder.img?.setOnClickListener {
            sportsClick.sportsClick(arrayList[holder.adapterPosition], holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addArray(array: ArrayList<DifferentSportsModel.DifferentSportsModelItem>) {
        arrayList.clear()
        arrayList.addAll(array)
        notifyDataSetChanged()
    }

    fun updateList(array: DifferentSportsModel.DifferentSportsModelItem, position: Int) {
        arrayList.set(position, array)
        notifyItemChanged(position)
    }

    fun sportsClick(sportsClick: SportsClick) {
        this.sportsClick = sportsClick
    }

    interface SportsClick {
        fun sportsClick(sports: DifferentSportsModel.DifferentSportsModelItem, position: Int)
    }

}