package com.faizzfanani.codetest_dansmultipro.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faizzfanani.codetest_dansmultipro.R
import com.faizzfanani.codetest_dansmultipro.ui.DetailActivity
import com.faizzfanani.jobssdk.model.DataJobs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_jobs.view.*

class JobsAdapter(private val list:ArrayList<DataJobs>) : RecyclerView.Adapter<JobsAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_jobs,parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Picasso.get().load(list[position].company_logo).into(holder.view.item_logo)
        holder.view.item_title.text = list[position].title
        holder.view.item_type.text = list[position].type
        holder.view.item_location.text = list[position].location
        //item click listener
        holder.view.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id", list[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}