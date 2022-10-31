package com.ci.act.ui.home.leaderBoard

import android.os.Bundle
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityLeaderBoardBinding

class LeaderBoardActivity:BaseActivity<ActivityLeaderBoardBinding,LeaderBoardView,LeaderBoardViewModel>(),LeaderBoardView{
    override fun getContentView(): Int = R.layout.activity_leader_board

    override fun setViewModelClass(): Class<LeaderBoardViewModel> {
        return LeaderBoardViewModel::class.java
    }

    override fun getNavigator(): LeaderBoardView = this

    override fun getBindingVariable(): Int = BR.leaderBoard

    override fun initViews(savedInstanceState: Bundle?) {

    }

}