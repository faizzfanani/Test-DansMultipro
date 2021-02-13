@file:Suppress("DEPRECATION")

package com.faizzfanani.codetest_dansmultipro.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.faizzfanani.codetest_dansmultipro.R
import com.faizzfanani.jobssdk.model.DataJobs
import com.faizzfanani.jobssdk.presenter.JobsPresenter
import com.faizzfanani.jobssdk.view.IJobsView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), IJobsView {
    private lateinit var loading : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //init views
        loading = ProgressDialog(this@DetailActivity)
        loading.setMessage("Loading data...")
        loading.show()
        //load data
        val jobsPresenter = JobsPresenter(this)
        val id = intent.getStringExtra("id")
        jobsPresenter.onLoadDetails(id!!)
        //event listener
        btn_back.setOnClickListener { super.onBackPressed() }
    }

    override fun onJobsLoaded(dataJob: MutableList<DataJobs>) {}

    override fun onDetailLoaded(dataJobs: DataJobs) {
        Picasso.get().load(dataJobs.company_logo).into(detail_image)
        detail_name.text = dataJobs.title
        detail_type.text = dataJobs.type
        detail_company.text = dataJobs.company
        detail_location.text = dataJobs.location
        detail_description.text = Html.fromHtml(dataJobs.description)
        loading.dismiss()
    }
}