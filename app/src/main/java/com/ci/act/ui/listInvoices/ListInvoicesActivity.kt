package com.ci.act.ui.listInvoices


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityListInvoicesBinding
import com.ci.act.ui.home.events.EventsActivity
import com.ci.act.ui.invoice.InvoicesActivity
import com.ci.act.ui.listInvoices.adapter.ListInvoicesAdapter
import com.ci.act.ui.listInvoices.model.ListInvoicesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListInvoicesActivity :
    BaseActivity<ActivityListInvoicesBinding, ListInvoicesView, ListInvoicesViewModel>(),
    ListInvoicesView {

    private var adapterListInvoices: ListInvoicesAdapter? = null
    private var liveListInvoices: ArrayList<ListInvoicesModel.ListInvoicesModelItem> = ArrayList()

    override fun getContentView(): Int = R.layout.activity_list_invoices

    override fun setViewModelClass(): Class<ListInvoicesViewModel> {
        return ListInvoicesViewModel::class.java
    }

    override fun getNavigator(): ListInvoicesView = this

    override fun getBindingVariable(): Int = BR.listInvoices


    override fun initViews(savedInstanceState: Bundle?) {
        recyclerView()
        setOnClickListener()
        setUpToolBar()
    }

    private fun setOnClickListener() {

    }

    private fun recyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvListInvoices)

        adapterListInvoices = ListInvoicesAdapter()
        val differentSportsJson = getDataFromJson()

        val gson = Gson()
        val listMoviesType = object : TypeToken<ListInvoicesModel>() {}.type
        liveListInvoices = gson.fromJson(differentSportsJson, listMoviesType)
        Log.e("hello", "${liveListInvoices}")
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapterListInvoices
        adapterListInvoices?.addArray(liveListInvoices)

    }

    private fun getDataFromJson(): String? {
        val jsonString: String
        try {
            jsonString =
                this.assets.open("list_invoices.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }


    private fun setUpToolBar() {
        mViewDataBinding?.toolBar?.let{
            it.txtToolbarHeading.text = "INVOICES"
            it.txtToolBarDummyIcon.visibility = View.INVISIBLE
            it.imgToolBarLeft.setImageResource(R.drawable.ic_back_arrow)
            it.imgToolBarLeft.setColorFilter(ContextCompat.getColor(this, R.color.light_black))
            it.imgToolBarRight.visibility = View.INVISIBLE

            it.imgToolBarLeft.setOnClickListener {
                val intent = Intent(this, InvoicesActivity::class.java)
                startActivity(intent)            }
        }
    }


}