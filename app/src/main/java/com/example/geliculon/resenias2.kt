package com.example.geliculon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class resenias2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resenias2)

        val id=intent.getIntExtra("tvid",0)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tmdbApi = retrofit.create(Api::class.java)

        tmdbApi.getSeriesReviews(id, "54e87eee285a99614f18a228f764e2ea").enqueue(object :
            Callback<ReviewResponse2> {
            override fun onResponse(call: Call<ReviewResponse2>, response: Response<ReviewResponse2>) {
                val reviews = response.body()?.results
                val recyclerView = findViewById<RecyclerView>(R.id.reviewsRecyclerView2)
                recyclerView.layoutManager = LinearLayoutManager(this@resenias2)
                val adapter = reviews?.let { SeriesReviewAdapter(it) }
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<ReviewResponse2>, t: Throwable) {
                // Maneja el error
            }
        })
    }
}

