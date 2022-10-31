package com.ci.act.ui.onboarding.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    private val introFragment: ArrayList<Fragment>
) : FragmentPagerAdapter(
    supportFragmentManager,
    FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    companion object {
        val BUNDLE_KEY_POSITION = "position"
    }

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable(BUNDLE_KEY_POSITION, position)
        introFragment[position].arguments = bundle
        return introFragment[position]
    }

    override fun getCount(): Int {
        return introFragment.size
    }

}