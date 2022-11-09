package com.ci.act.ui.home.settingsPage.fragments.radiusThirdFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentRadiusThirdBinding

class RadiusThirdFragment :
    BaseFragment<FragmentRadiusThirdBinding, RadiusThirdView, RadiusThirdViewModel>(),
    RadiusThirdView {
    override fun getContentView(): Int = R.layout.fragment_radius_third

    override fun setViewModel(): RadiusThirdViewModel? = RadiusThirdViewModel()

    override fun getBindingVariable(): Int = BR.radiusThirdFragment

    override fun getNavigator(): RadiusThirdView = this

    override fun initViews(savedInstanceState: Bundle?) {

        mViewDataBinding?.seekBar?.max = 500
        mViewDataBinding?.seekBar?.progress = 5

        mViewDataBinding?.progressBar?.layoutParams?.width = mViewDataBinding?.seekBar?.max!!+180
        mViewDataBinding?.progressBar?.layoutParams?.height = mViewDataBinding?.seekBar?.max!!+180

        mViewDataBinding?.progressBarProgress?.layoutParams?.width = 185
        mViewDataBinding?.progressBarProgress?.layoutParams?.height = 185

        mViewDataBinding?.seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mViewDataBinding?.textView?.text = "$progress miles"
                mViewDataBinding?.seekbarTextView?.text = "$progress miles"

                val profileImageWidth = mViewDataBinding?.profileImage?.measuredWidth
                Log.e("profile_width", "$profileImageWidth")
                val profileImageHeight = mViewDataBinding?.profileImage?.measuredHeight

                mViewDataBinding?.progressBar?.layoutParams?.width = mViewDataBinding?.seekBar?.max!!+profileImageWidth!!+1
                mViewDataBinding?.progressBar?.layoutParams?.height = mViewDataBinding?.seekBar?.max!!+profileImageWidth!!+1

                mViewDataBinding?.progressBarProgress?.layoutParams?.width = progress+profileImageWidth!!+1
                mViewDataBinding?.progressBarProgress?.layoutParams?.height = progress+profileImageHeight!!+1
                mViewDataBinding?.progressBarProgress?.requestLayout()

                val x = seekBar.thumb.bounds.left
                mViewDataBinding?.seekbarTextView?.x = (x+10).toFloat()

//                val value = seekBar.progress
//                if(value!=0) {
//                    val cur = seekBar.width/seekBar.max
//                    mViewDataBinding?.seekbarTextView?.x = (cur * value).toFloat() +10
//                }
//                mViewDataBinding?.seekbarTextView?.y = seekBar.pivotY + 10
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

}