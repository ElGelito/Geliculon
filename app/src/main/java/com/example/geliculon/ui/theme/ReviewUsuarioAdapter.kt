package com.example.geliculon
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ReviewUsuarioAdapter(private val reviews: List<DetallesPelicula.Review>) : RecyclerView.Adapter<ReviewUsuarioAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reviewusuario, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.review_title)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.review_rating)
        private val reviewTextView: TextView = itemView.findViewById(R.id.review_text)
        private val userIdTextView:TextView=itemView.findViewById(R.id.user_id_text)
        private val userNameTextView:TextView=itemView.findViewById(R.id.user_name_text)
        private val tipoReseñaTextView:TextView=itemView.findViewById(R.id.tipo_resena_text)



        fun bind(review: DetallesPelicula.Review) {
            titleTextView.text = review.title
            ratingBar.rating = review.rating
            reviewTextView.text = review.reviewText
            userIdTextView.text = review.userId
            userNameTextView.text = review.userName
            tipoReseñaTextView.text = review.tipoReseña
        }

    }
}
