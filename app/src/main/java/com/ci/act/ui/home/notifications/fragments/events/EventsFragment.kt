package com.ci.act.ui.home.notifications.fragments.events

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentEventsBinding


class EventsFragment:BaseFragment<FragmentEventsBinding,EventsFragmentView,EventsFragmentViewModel>(), EventsFragmentView {
    override fun getContentView(): Int = R.layout.fragment_events
    override fun setViewModel(): EventsFragmentViewModel = EventsFragmentViewModel()
    override fun getBindingVariable(): Int = BR.eventsViewModel
    override fun getNavigator(): EventsFragmentView = this

    override fun initViews(savedInstanceState: Bundle?) {

    }
}