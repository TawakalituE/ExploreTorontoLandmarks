package com.example.euniceadinlewa_comp304lab4_ex1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LandmarkAdapter(
    private val landmarks: List<Landmark>,
    private val clickListener: (Landmark) -> Unit
) : RecyclerView.Adapter<LandmarkAdapter.LandmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_landmark, parent, false)
        return LandmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        val landmark = landmarks[position]
        holder.bind(landmark, clickListener)
    }

    override fun getItemCount() = landmarks.size

    class LandmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewLandmarkName)
        private val addressTextView: TextView = itemView.findViewById(R.id.textViewLandmarkAddress)

        fun bind(landmark: Landmark, clickListener: (Landmark) -> Unit) {
            nameTextView.text = landmark.name
            addressTextView.text = landmark.address
            itemView.setOnClickListener { clickListener(landmark) }
        }
    }
}
