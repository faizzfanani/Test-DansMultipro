@file:Suppress("DEPRECATION")

package com.faizzfanani.codetest_dansmultipro.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizzfanani.codetest_dansmultipro.R
import com.faizzfanani.codetest_dansmultipro.adapter.JobsAdapter
import com.faizzfanani.jobssdk.model.DataJobs
import com.faizzfanani.jobssdk.presenter.JobsPresenter
import com.faizzfanani.jobssdk.view.IJobsView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), IJobsView {
    private var filterLoaded = false
    private lateinit var loading : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        //init views
        loading = ProgressDialog(this@ListActivity)
        loading.setMessage("Loading data...")
        loading.show()
        rv_jobs.setHasFixedSize(true)
        rv_jobs.layoutManager = LinearLayoutManager(applicationContext)

        val jobsPresenter = JobsPresenter(this)
        jobsPresenter.onLoadJobs()

        //event listener
        image_arrow.setOnClickListener {
            loadFilter()
        }
    }
    private fun loadFilter(){
        if (!filterLoaded){
            container_filter.visibility = View.VISIBLE
            image_arrow.setImageResource(R.drawable.ic_arrow_up)
            filterLoaded = true
        }else if (filterLoaded){
            container_filter.visibility = View.GONE
            image_arrow.setImageResource(R.drawable.ic_arrow_down)
            filterLoaded = false
        }
    }
    override fun onJobsLoaded(dataJob: MutableList<DataJobs>) {

        if (dataJob.size > 0){
            val adapter = JobsAdapter(dataJob as ArrayList<DataJobs>)
            adapter.notifyDataSetChanged()
            rv_jobs.adapter = adapter
            loading.dismiss()
        }else{
            label_empty.visibility = View.VISIBLE
            loading.dismiss()
        }
    }

    override fun onDetailLoaded(dataJobs: DataJobs) {}
}