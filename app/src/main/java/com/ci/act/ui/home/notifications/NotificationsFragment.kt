package com.ci.act.ui.home.notifications

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.ci.act.BR

import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentNotificationsBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.home.notifications.adapter.NotificationViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_tool_bar.view.*

class NotificationsFragment :BaseFragment<FragmentNotificationsBinding,NotificationsView,NotificationsViewModel>(), NotificationsView{

    private lateinit var adapter : NotificationViewPagerAdapter
    var isSelectedClick = true
    private val sharedSelectedViewModel: NotificationsViewModel by activityViewModels()


    override fun getContentView(): Int = R.layout.fragment_notifications

    override fun setViewModel(): NotificationsViewModel= NotificationsViewModel()
    override fun getBindingVariable(): Int = BR.notification

    override fun getNavigator(): NotificationsView = this

    override fun initViews(savedInstanceState: Bundle?) {
        setUpToolBar()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        adapter = NotificationViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        mViewDataBinding?.viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mViewDataBinding?.viewPager?.adapter = adapter
        mViewDataBinding?.viewPager?.viewTreeObserver

        TabLayoutMediator(mViewDataBinding?.tabLayout!!, mViewDataBinding?.viewPager!!) { tab, position ->
            when (position) {
                0 -> tab.text = "All"
                1 -> tab.text = "Events"
                2 -> tab.text = "Payment"
                3 -> tab.text = "Others"
            }
        }.attach()
    }

    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "NOTIFICATIONS"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_back_arrow)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black))
            it.imgToolBarRight.visibility = View.INVISIBLE
            it.txtToolBarMiddleIcon.text = "Se"

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(requireContext(),EventsActivity::class.java)
                startActivity(intent)
            }

            it.txtToolBarSelect.visibility = View.VISIBLE
            it.txtToolBarSelect.text = "Select"
            it.txtToolBarSelect.textSize = 14f
            it.txtToolBarSelect.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainColor))
            it.imgToolBarRight.setColorFilter(ContextCompat.getColor(requireContext(), R.color.mainColor))
            it.txtToolBarSelect.setOnClickListener {
                if (isSelectedClick) {
                    mViewDataBinding?.toolBar?.txtToolBarSelect?.text = "Cancel"
                    sharedSelectedViewModel.setSelect(true)
                } else {
                    mViewDataBinding?.toolBar?.txtToolBarSelect?.text = "Select"
                    sharedSelectedViewModel.setSelect(false)
                }
                isSelectedClick = !isSelectedClick
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedSelectedViewModel.setSelect(false)
    }

    override fun addObservables() {
    }

}