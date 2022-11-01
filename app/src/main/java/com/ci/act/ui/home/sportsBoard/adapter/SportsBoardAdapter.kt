package com.ci.act.ui.home.sportsBoard.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ci.act.R
import com.ci.act.ui.home.sportsBoard.model.SportsBoardModel

class SportsBoardAdapter : RecyclerView.Adapter<SportsBoardAdapter.SportsBoardViewHolder>() {

    private val sportsList: ArrayList<SportsBoardModel.SportsBoardModelItem> = ArrayList()


    class SportsBoardViewHolder(sportsBoardView: View) : RecyclerView.ViewHolder(sportsBoardView) {
        var imgSportsBoardKing: ImageView? = sportsBoardView.findViewById(R.id.imgSportsBoardKing)
        var imgSportsBoardPicture: ImageView? =
            sportsBoardView.findViewById(R.id.imgSportsBoardPicture)
        var txtSportsBoardMaria: TextView? = sportsBoardView.findViewById(R.id.txtSportsBoardMaria)
        var txtSportsBoardTime: TextView? = sportsBoardView.findViewById(R.id.txtSportsBoardTime)
        var txtSportsBoardB17: TextView? = sportsBoardView.findViewById(R.id.txtSportsBoardB17)
        var txtPositionSportsBoard: TextView? =
            sportsBoardView.findViewById(R.id.txtPositionSportsBoard)
        var mainSportsBoardCardView: ConstraintLayout? =
            sportsBoardView.findViewById(R.id.mainSportsBoardCardView)


        fun sportsBoardBindItems(sportsBoardModelItem: SportsBoardModel.SportsBoardModelItem) {
            txtSportsBoardMaria?.text = sportsBoardModelItem.userName
            txtSportsBoardTime?.text = sportsBoardModelItem.time
            txtSportsBoardB17?.text = sportsBoardModelItem.userId

            val resId: Int = itemView.context.resources.getIdentifier(
                sportsBoardModelItem.profileImage,
                "drawable",
                itemView.context.packageName
            )
            val profile = ResourcesCompat.getDrawable(itemView.resources, resId, null);
            Glide.with(imgSportsBoardPicture!!)
                .load(profile)
                .error(R.drawable.profile_picture)
                .into(imgSportsBoardPicture!!)

            txtPositionSportsBoard?.text = sportsBoardModelItem.rank

            if (sportsBoardModelItem.rank == "1.") {
                imgSportsBoardKing?.visibility = View.VISIBLE
                txtPositionSportsBoard?.visibility = View.INVISIBLE
            } else {
                imgSportsBoardKing?.visibility = View.INVISIBLE
                txtPositionSportsBoard?.visibility = View.VISIBLE
            }

            if (sportsBoardModelItem.userName == "You") {
                mainSportsBoardCardView?.setBackgroundColor(Color.LTGRAY)
                txtSportsBoardB17?.setTextColor(Color.WHITE)
                txtPositionSportsBoard?.setTextColor(Color.RED)
                txtSportsBoardB17?.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.rounded_button)
            } else {
                mainSportsBoardCardView?.setBackgroundColor(Color.WHITE)
                txtSportsBoardB17?.setTextColor(Color.RED)
                txtPositionSportsBoard?.setTextColor(Color.BLACK)
                txtSportsBoardB17?.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_line_red)


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsBoardViewHolder {
        val sportsBoard =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.inflate_sports_board, parent, false)
        return SportsBoardAdapter.SportsBoardViewHolder(sportsBoard)
    }

    override fun onBindViewHolder(holder: SportsBoardViewHolder, position: Int) {
        holder.sportsBoardBindItems(sportsList[holder.adapterPosition])

    }

    override fun getItemCount(): Int {
        return sportsList.size
    }

    fun addArray(array: ArrayList<SportsBoardModel.SportsBoardModelItem>) {
        sportsList.clear()
        sportsList.addAll(array)
        notifyDataSetChanged()
    }
}