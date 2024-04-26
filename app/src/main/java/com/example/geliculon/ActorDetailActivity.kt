package com.example.geliculon

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.geliculon.ActorDetailsResponse
import com.example.geliculon.Api
import com.example.geliculon.R
import com.example.geliculon.databinding.ActivityActorDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActorDetailActivity : AppCompatActivity() {
    private lateinit var actorProfileImageView: ImageView
    private lateinit var actorNameTextView: TextView
    private lateinit var actorBioTextView: TextView

    private val tmdbApi: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityActorDetailBinding=
        ActivityActorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actorProfileImageView = findViewById(R.id.actorProfileImageView)
        actorNameTextView = findViewById(R.id.actorNameTextView)
        actorBioTextView = findViewById(R.id.actorBioTextView)

        val actorId = intent.getIntExtra("actor_id", -1)
        if (actorId != -1) {
            fetchActorDetails(actorId)
        } else {
            // Manejar el caso en el que no se proporcionó un ID de actor válido
        }
    }

    private fun fetchActorDetails(actorId: Int) {
        val apiKey = "54e87eee285a99614f18a228f764e2ea"

        tmdbApi.getActorDetails(actorId, apiKey).enqueue(object : Callback<ActorDetailsResponse> {
            override fun onResponse(call: Call<ActorDetailsResponse>, response: Response<ActorDetailsResponse>) {
                if (response.isSuccessful) {
                    val actorDetails = response.body()
                    if (actorDetails != null) {
                        showActorDetails(actorDetails)
                    } else {
                        // Manejar el caso en el que no se obtuvieron detalles del actor
                    }
                } else {
                    // Manejar errores de respuesta de la API
                }
            }

            override fun onFailure(call: Call<ActorDetailsResponse>, t: Throwable) {
                // Manejar errores de red o de solicitud
            }
        })
    }

    private fun showActorDetails(actorDetails: ActorDetailsResponse) {
        actorNameTextView.text = actorDetails.name
        actorBioTextView.text = actorDetails.biography

        val profileUrl = "https://image.tmdb.org/t/p/w500/${actorDetails.profilePath}"
        Glide.with(this)
            .load(profileUrl)
            .into(actorProfileImageView)
    }
}
