package com.ci.act.ui.home.eventsReport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.home.eventsReport.model.EventsReportModel

class EventsReportAdapter : RecyclerView.Adapter<EventsReportAdapter.EventsReportViewHolder>() {

    private val reportsList: ArrayList<EventsReportModel.EventsReportModelItem> = ArrayList()

    class EventsReportViewHolder(eventsView: View) : RecyclerView.ViewHolder(eventsView) {

        var textEventsGameReport: TextView? = eventsView.findViewById(R.id.txtEventsGameName)
        var txtEventsReportsRanks: TextView? = eventsView.findViewById(R.id.txtEventsReportsRanks)
        var txtEventsPosition: TextView? = eventsView.findViewById(R.id.txtEventsPosition)
        var txtEventsReportsRanking: TextView? =
            eventsView.findViewById(R.id.txtEventsReportsRanking)
        var txtEventsReportsSpeedNumber: TextView? =
            eventsView.findViewById(R.id.txtEventsReportsSpeedNumber)
        var txtEventsReportsSpeed: TextView? = eventsView.findViewById(R.id.txtEventsReportsSpeed)
        var txtEventsReportsSecondsNumber: TextView? =
            eventsView.findViewById(R.id.txtEventsReportsSecondsNumber)
        var txtEventsReportsSeconds: TextView? =
            eventsView.findViewById(R.id.txtEventsReportsSeconds)
        var txtEventsReportsTestedNumber: TextView? =
            eventsView.findViewById(R.id.txtEventsReportsTestedNumber)
        var txtEventsReportsTested: TextView? = eventsView.findViewById(R.id.txtEventsReportsTested)

        fun eventsReportBindItems(eventsReportModelItem: EventsReportModel.EventsReportModelItem) {
            textEventsGameReport?.text = eventsReportModelItem.eventName
            txtEventsReportsRanks?.text = eventsReportModelItem.rank
            txtEventsPosition?.text = eventsReportModelItem.rankSupText
            txtEventsReportsSpeed?.text = eventsReportModelItem.test1Name
            txtEventsReportsSpeedNumber?.text = eventsReportModelItem.test1Score
            txtEventsReportsSecondsNumber?.text = eventsReportModelItem.test2Score
            txtEventsReportsSeconds?.text = eventsReportModelItem.test2Name
            txtEventsReportsTestedNumber?.text = eventsReportModelItem.totalTest
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsReportViewHolder {
        val EventsReport = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_events_report, parent, false)
        return EventsReportAdapter.EventsReportViewHolder(EventsReport)
    }

    override fun onBindViewHolder(holder: EventsReportViewHolder, position: Int) {
        holder.eventsReportBindItems(reportsList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return reportsList.size
    }

    fun addArray(array: ArrayList<EventsReportModel.EventsReportModelItem>) {
        reportsList.clear()
        reportsList.addAll(array)
        notifyDataSetChanged()
    }
}