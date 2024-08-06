package com.example.euniceadinlewa_comp304lab4_ex1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LandmarkTypeAdapter(private val landmarkTypes: List<String>, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<LandmarkTypeAdapter.LandmarkTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return LandmarkTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandmarkTypeViewHolder, position: Int) {
        val landmarkType = landmarkTypes[position]
        holder.bind(landmarkType, clickListener)
    }

    override fun getItemCount(): Int = landmarkTypes.size

    class LandmarkTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(landmarkType: String, clickListener: (String) -> Unit) {
            (itemView as TextView).text = landmarkType
            itemView.setOnClickListener { clickListener(landmarkType) }
        }
    }
}
