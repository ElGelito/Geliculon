package com.example.geliculon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SeriesReviewAdapter(private val reviews: List<SeriesReview>) :
    RecyclerView.Adapter<SeriesReviewAdapter.SeriesReviewViewHolder>() {

    inner class SeriesReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return SeriesReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeriesReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.authorTextView.text = review.author
        holder.contentTextView.text = review.content
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}
