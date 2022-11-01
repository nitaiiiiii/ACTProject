package com.ci.act.ui.home.myRegisteredEvents.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.home.myRegisteredEvents.model.RegisteredModel

class MyRegisteredAdapter : RecyclerView.Adapter<MyRegisteredAdapter.MyRegisteredViewHolder>() {

    private val registeredList: ArrayList<RegisteredModel.RegisteredModelItem.Events> = ArrayList()

    class MyRegisteredViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgACT: ImageView? = view.findViewById(R.id.registeredACT)
        var txtEventsGamer: TextView? = view.findViewById(R.id.txtRegistered)
        var txtBlackEvents: TextView? = view.findViewById(R.id.txtRegisteredBlackText)
        var txtBlackEventsRank: TextView? = view.findViewById(R.id.txtRegisteredBlackTextRank)

        var txtEventsGame: TextView? = view.findViewById(R.id.txtEventsGameRegistered)
        var imgIGames: ImageView? = view.findViewById(R.id.imgInflateLocationRegistered)
        var txtGamesGame: TextView? = view.findViewById(R.id.txtInflateLocationRegistered)
        var txtGamesTwo: TextView? = view.findViewById(R.id.txtLiveEventsOneReg)
        val txtEventsRegisteredPeople: TextView? = view.findViewById(R.id.txtEventsRegisteredPeople)
        val txtRegisteredSubScript: TextView? = view.findViewById(R.id.txtRegisteredSubScript)
        val imgLeaderBoard: ImageView? = view.findViewById(R.id.imgLeaderBoard)
        val txtLeaderBoard: TextView? = view.findViewById(R.id.txtLeaderBoard)
        val imgLeaderBoardReport: ImageView? = view.findViewById(R.id.imgLeaderBoardReport)
        val txtLeaderBoardReport: TextView? = view.findViewById(R.id.txtLeaderBoardReport)
        val btnGreyCancel: Button? = view.findViewById(R.id.btnGreyCancel)
        var txtRegisteredRanker: TextView? = view.findViewById(R.id.txtRegisteredRanker)


        fun myRegisteredBindItems(registeredModelItem: RegisteredModel.RegisteredModelItem.Events) {
            txtEventsGame?.text = registeredModelItem.eventName
            Log.e("name", "${txtEventsGame}")
            txtGamesGame?.text = registeredModelItem.eventLocation
            Log.e("location", "${txtGamesGame}")

            txtGamesTwo?.text = registeredModelItem.eventDate
            Log.e("date", "${txtGamesTwo}")

            txtEventsGamer?.text = registeredModelItem.rank
            Log.e("rank", "${txtEventsGamer}")

            txtEventsRegisteredPeople?.text = registeredModelItem.totalParticipated
            Log.e("participate", "${txtEventsRegisteredPeople}")

            txtRegisteredSubScript?.text = registeredModelItem.subScript
            Log.e("subscript", "${txtRegisteredSubScript}")

            if (registeredModelItem.isEventCompleted == true) {
                imgLeaderBoard?.visibility = View.VISIBLE
                txtLeaderBoard?.visibility = View.VISIBLE
                imgLeaderBoardReport?.visibility = View.VISIBLE
                txtLeaderBoardReport?.visibility = View.VISIBLE
                btnGreyCancel?.visibility = View.GONE
                txtEventsGamer?.visibility = View.VISIBLE
                txtRegisteredRanker?.visibility = View.GONE
                txtRegisteredSubScript?.visibility = View.VISIBLE
                txtBlackEvents?.visibility = View.GONE
                txtBlackEventsRank?.visibility = View.VISIBLE


            } else {
                imgLeaderBoard?.visibility = View.GONE
                txtLeaderBoard?.visibility = View.GONE
                imgLeaderBoardReport?.visibility = View.GONE
                txtLeaderBoardReport?.visibility = View.GONE
                btnGreyCancel?.visibility = View.VISIBLE
                txtEventsGamer?.visibility = View.GONE
                txtRegisteredRanker?.visibility = View.VISIBLE
                txtRegisteredSubScript?.visibility = View.GONE
                txtBlackEvents?.visibility = View.VISIBLE
                txtBlackEventsRank?.visibility = View.GONE
                txtEventsGamer?.text = "${registeredModelItem.daysLeft}\nDAYS"
                txtBlackEvents?.text = "DAYS\nTO GO"
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRegisteredViewHolder {
        val MyRegister =
            LayoutInflater.from(parent.context).inflate(R.layout.events_registered, parent, false)
        return MyRegisteredViewHolder(MyRegister)
    }

    override fun onBindViewHolder(holder: MyRegisteredViewHolder, position: Int) {
        holder.myRegisteredBindItems(registeredList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return registeredList.size
    }


    fun addArray(array: ArrayList<RegisteredModel.RegisteredModelItem.Events>) {
        registeredList.clear()
        registeredList.addAll(array)
        notifyDataSetChanged()
    }
}