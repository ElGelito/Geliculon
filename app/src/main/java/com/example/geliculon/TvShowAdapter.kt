package com.example.geliculon
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.coroutines.NonDisposableHandle.parent

class TvShowAdapter(private var tvShow: MutableList<TvShow>,private val onTvShowClick: (tvShow: TvShow) -> Unit) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tvshow, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, it: Int) {
        holder.bind(tvShow[it])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun appendTvShows(tvShows: List<TvShow>) {
        this.tvShow.addAll(tvShows)
        notifyItemRangeInserted(
            this.tvShow.size,
            tvShows.size - 1
        )
        notifyDataSetChanged()
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_tv_show_poster)
        fun bind(tvShow: TvShow) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${tvShow.posterPath}")
                .transform(CenterCrop())
                .into(poster)

            itemView.setOnClickListener { onTvShowClick.invoke(tvShow) }
        }
    }
}


