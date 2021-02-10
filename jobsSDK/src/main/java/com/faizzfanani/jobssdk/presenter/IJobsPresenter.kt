package com.faizzfanani.jobssdk.presenter

interface IJobsPresenter {
    fun onLoadJobs()
    fun onLoadDetails(id:String)
}