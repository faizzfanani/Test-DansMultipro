package com.faizzfanani.jobssdk.view

import com.faizzfanani.jobssdk.model.DataJobs

interface IJobsView {
    fun onJobsLoaded(dataJob: MutableList<DataJobs>)
    fun onDetailLoaded(dataJobs: DataJobs)
}