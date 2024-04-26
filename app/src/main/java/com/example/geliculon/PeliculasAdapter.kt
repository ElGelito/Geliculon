package com.example.geliculon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class PeliculasAdapter(private var movies: MutableList<Pelicula>,private val onMovieClick: (movie: Pelicula) -> Unit): RecyclerView.Adapter<PeliculasAdapter.PeliculasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return PeliculasViewHolder(view)
    }
    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        holder.bind(movies[position])
    }
    fun appendMovies(movies: List<Pelicula>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1
        )
    }

    inner class PeliculasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        fun bind(movie: Pelicula) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.rutaPoster}")
                .transform(CenterCrop())
                .into(poster)
                itemView.setOnClickListener { onMovieClick.invoke(movie) }
        }
    }

}