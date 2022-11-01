package com.ci.act.ui.home.mainEventScreenRegister

import androidx.fragment.app.Fragment

interface Communicator {
    fun loadFragments(fragment: Fragment, check: Boolean, fragmentPage: Int)
}