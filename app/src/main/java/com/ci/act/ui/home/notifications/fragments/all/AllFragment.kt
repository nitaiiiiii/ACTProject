package com.ci.act.ui.home.notifications.fragments.all

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentAllBinding


class AllFragment: BaseFragment<FragmentAllBinding,AllFragmentView,AllFragmentViewModel>(),AllFragmentView {
    override fun getContentView(): Int = R.layout.fragment_all

    override fun setViewModel(): AllFragmentViewModel? = AllFragmentViewModel()

    override fun getBindingVariable(): Int = BR.all

    override fun getNavigator(): AllFragmentView = this

    override fun initViews(savedInstanceState: Bundle?) {

    }


}