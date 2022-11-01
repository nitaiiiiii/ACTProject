package com.ci.act.ui.home.faqList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityFaqListBinding
import com.ci.act.ui.home.contactUs.ContactUsActivity
import com.ci.act.ui.home.faqList.adapter.FaqListAdapter
import com.ci.act.ui.home.faqList.model.FaqListModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FaqListActivity : BaseActivity<ActivityFaqListBinding, FaqListView, FaqListViewModel>(),
    FaqListView {

    private var adapterFAQs: FaqListAdapter? = null
    private var liveFAQs: ArrayList<FaqListModel.FaqListModelItem> = ArrayList()


    override fun getContentView(): Int = R.layout.activity_faq_list

    override fun setViewModelClass(): Class<FaqListViewModel> {
        return FaqListViewModel::class.java
    }

    override fun getNavigator(): FaqListView = this

    override fun getBindingVariable(): Int = BR.faqList

    override fun initViews(savedInstanceState: Bundle?) {
        recyclerView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mViewDataBinding?.btnRelevantQuestions?.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvListFAQs)

        adapterFAQs = FaqListAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<FaqListModel>() {}.type
        liveFAQs = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${liveFAQs}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapterFAQs
        adapterFAQs?.addArray(liveFAQs)

    }

    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString =
                this.assets.open("faq_questions.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }


}