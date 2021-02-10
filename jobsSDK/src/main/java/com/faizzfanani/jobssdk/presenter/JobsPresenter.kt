package com.faizzfanani.jobssdk.presenter

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.faizzfanani.jobssdk.model.DataJobs
import com.faizzfanani.jobssdk.utils.API
import com.faizzfanani.jobssdk.view.IJobsView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject


class JobsPresenter(internal var iJobsView: IJobsView) : IJobsPresenter {
    private val gson = Gson()
    private val jobsType = object : TypeToken<MutableList<DataJobs>>() {}.type
    private lateinit var jobsList: MutableList<DataJobs>
    override fun onLoadJobs() {
        AndroidNetworking.get(API.URL+".json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray?) {

                        jobsList = gson.fromJson(response.toString(), jobsType)
                        Log.i("getjobs", response.toString())
                        if (jobsList.size > 0) {
                            iJobsView.onJobsLoaded(jobsList)
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.i("geterror", anError!!.errorBody)
                    }
                })
    }
    override fun onLoadDetails(id: String) {
        var dataJobs:DataJobs
        AndroidNetworking.get(API.URL+"/"+id+".json")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    dataJobs = gson.fromJson(response.toString(), DataJobs::class.java)
                    if (dataJobs.id.isNotEmpty()) {
                        iJobsView.onDetailLoaded(dataJobs)
                    }
                }
                override fun onError(anError: ANError?) {
                    Log.i("geterror", anError!!.errorBody)
                }
            })
    }
}