package com.example.geliculon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ActorAdapter(
    private var actors: List<Actor>,
    private val onItemClick: (Actor) -> Unit
) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.bind(actor)

        holder.itemView.setOnClickListener { onItemClick(actor) }

    }

    override fun getItemCount(): Int = actors.size

    fun updateActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actorName: TextView = itemView.findViewById(R.id.actorName)
        private val actorProfile: ImageView = itemView.findViewById(R.id.actorProfile)

        fun bind(actor: Actor) {
            actorName.text = actor.name
            val profileUrl = "https://image.tmdb.org/t/p/w500/${actor.profilePath}"
            Glide.with(itemView)
                .load(profileUrl)
                .into(actorProfile)
        }
    }
}
