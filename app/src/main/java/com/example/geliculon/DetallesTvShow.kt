package com.example.geliculon

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TV_SHOW_BACKDROP = "extra_tv_show_backdrop"
const val TV_SHOW_POSTER = "extra_tv_show_poster"
const val TV_SHOW_TITLE = "extra_tv_show_title"
const val TV_SHOW_RATING = "extra_tv_show_rating"
const val TV_SHOW_RELEASE_DATE = "extra_tv_show_release_date"
const val TV_SHOW_OVERVIEW = "extra_tv_show_overview"
const val TV_SHOW_ORIGINALLANGUAGE = "extra_tv_show_originallanguage"
const val TV_SHOW_ID = "extra_tv_show_id"

class DetallesTvShow : AppCompatActivity() {


    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    private lateinit var backdrop: ImageView
    private lateinit var idiomaoriginal: TextView
    private lateinit var addToWatchList: Button

    private lateinit var actorAdapter: ActorAdapter
    private lateinit var rvActors: RecyclerView


    private var tvShowId = 0L
    private var tvShowBackdrop = ""
    private var tvShowPoster = ""
    private var tvShowTitle = ""
    private var tvShowRating = 0f
    private var tvShowReleaseDate = ""
    private var tvShowOverview = ""
    private var tvShowOriginalLanguage=""

    private lateinit var rvPlataformas: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_tv_show)
        var tipousuario=intent.getIntExtra("tipousuario",0)

        idiomaoriginal=findViewById(R.id.idiomita)
        backdrop = findViewById(R.id.tv_show_backdrop)
        poster = findViewById(R.id.tv_show_poster)
        title = findViewById(R.id.tv_show_title)
        rating = findViewById(R.id.tv_show_rating)
        releaseDate = findViewById(R.id.tv_show_release_date)
        overview = findViewById(R.id.tv_show_overview)
        addToWatchList = findViewById(R.id.add_to_watch_list)


        val extras = intent.extras
        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }

        val trailer:Button = findViewById(R.id.trailer2)
        val idvideo= extras!!.getLong(TV_SHOW_ID)
        trailer.setOnClickListener{

            loadTvShowTrailer(idvideo)
        }


        var botonresenias:Button=findViewById(R.id.button8)

        botonresenias.setOnClickListener {
            if(tipousuario==2){
                Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
            }else{
                val intent=Intent(this,resenias2::class.java)
                intent.putExtra("tvid",idvideo.toInt())
                intent.putExtra("tvtitle",tvShowTitle)
                startActivity(intent)
            }

        }


        rvActors = findViewById(R.id.rvActors)
        actorAdapter = ActorAdapter(emptyList()) { actor ->
            // Implementa el código para abrir ActorDetailActivity con la información del actor
            val intent = Intent(this, ActorDetailActivity::class.java)
            intent.putExtra("actor_id", actor.id)
            startActivity(intent)
        }
        rvActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvActors.adapter = actorAdapter

        // Obtiene los actores utilizando la API de TMDB
        val apiKey = "54e87eee285a99614f18a228f764e2ea"

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tmdbService = retrofit.create(Api::class.java)
        val call = tmdbService.getTVShowCredits(idvideo.toString(), apiKey)

        call.enqueue(object : Callback<TVShowCreditsResponse> {
            override fun onResponse(
                call: Call<TVShowCreditsResponse>,
                response: Response<TVShowCreditsResponse>
            ) {
                if (response.isSuccessful) {
                    val credits = response.body()?.cast ?: emptyList()
                    actorAdapter.updateActors(credits)
                } else {
                    // Manejo de errores
                }
            }

            override fun onFailure(call: Call<TVShowCreditsResponse>, t: Throwable) {
                // Manejo de errores
            }
        })

        getSeriesStreamingPlatforms(idvideo)
        getSimilarSeries(idvideo,intent.getStringExtra("usuario").toString(),tipousuario)

        val botonusuarioreseñas = findViewById<Button>(R.id.reviews_button)
        botonusuarioreseñas.setOnClickListener {
            if (tipousuario == 2) {
                Toast.makeText(this, "Inicia Sesión Primero", Toast.LENGTH_LONG).show()
            } else {
                showReviewDialog(intent.getStringExtra("usuario").toString())
            }
        }




    }
    private fun showReviewDialog(usuario: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_review, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Escribir Reseña")
            .setPositiveButton("Guardar", null)
            .setNegativeButton("Cancelar", null)
        val alertDialog = dialogBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                saveReview(dialogView,usuario)
                alertDialog.dismiss()
            }
        }
        alertDialog.show()
    }

    private fun saveReview(dialogView: View, usuario: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val userName = usuario
            val ratingBar = dialogView.findViewById<RatingBar>(R.id.dialog_rating_bar)
            val reviewText = dialogView.findViewById<EditText>(R.id.dialog_review_text).text.toString()

            // Obtener los datos de la película
            val TvShowId = intent.getLongExtra(TV_SHOW_ID, 0L)
            val TvShowTitle = intent.getStringExtra(TV_SHOW_TITLE)

            // Crear un objeto Re deview
            val review = DetallesPelicula.Review(
                userId,
                userName,
                TvShowId,
                TvShowTitle,
                ratingBar.rating,
                reviewText,
            "Serie"
            )

            // Guardar la reseña en Firestore
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("reviews")
                .add(review)
                .addOnSuccessListener {
                    Toast.makeText(this, "Reseña guardada exitosamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al guardar la reseña", Toast.LENGTH_SHORT).show()
                }
        }
    }





    private fun populateDetails(extras: Bundle) {
        tvShowId = extras.getLong(TV_SHOW_ID)
        tvShowBackdrop = extras.getString(TV_SHOW_BACKDROP, "")
        tvShowPoster = extras.getString(TV_SHOW_POSTER, "")
        tvShowTitle = extras.getString(TV_SHOW_TITLE, "")
        tvShowRating = extras.getFloat(TV_SHOW_RATING, 0f)
        tvShowReleaseDate = extras.getString(TV_SHOW_RELEASE_DATE, "")
        tvShowOverview = extras.getString(TV_SHOW_OVERVIEW, "")
        tvShowOriginalLanguage=extras.getString(TV_SHOW_ORIGINALLANGUAGE,"")

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280$tvShowBackdrop")
            .transform(CenterCrop())
            .into(backdrop)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342$tvShowPoster")
            .transform(CenterCrop())
            .into(poster)
        title.text = tvShowTitle
        rating.rating = tvShowRating / 2
        releaseDate.text = tvShowReleaseDate
        overview.text = tvShowOverview
        idiomaoriginal.text=tvShowOriginalLanguage
        val tvShow = getTvShow(tvShowId)
        if (tvShow == null) {
            addToWatchList.text = getString(R.string.add_to_watch_list)
        } else {
            addToWatchList.text = getString(R.string.remove_from_watch_list)
        }

}


    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "geliculonn.db"
        ).allowMainThreadQueries().build()
    }

    private fun getTvShow(id: Long): TvShowEntity? {
        return db.tvShowDao().findById(id)
    }

    override fun onStart() {
        super.onStart()
        addToWatchList.setOnClickListener {
            if(intent.getIntExtra("tipousuario",0)==2){
                Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
            }else{
                if (getTvShow(tvShowId) == null) {
                    val entity = TvShowEntity(
                        tvShowId,
                        tvShowTitle,
                        tvShowOverview,
                        tvShowPoster,
                        tvShowBackdrop,
                        tvShowRating,
                        tvShowReleaseDate,
                        tvShowOriginalLanguage
                    )
                    db.tvShowDao().insert(entity)
                    addToWatchList.text = getString(R.string.remove_from_watch_list)
                } else {
                    db.tvShowDao().delete(tvShowId)
                    addToWatchList.text =   getString(R.string.add_to_watch_list)
                }
            }

        }
    }

    private fun loadTvShowTrailer(tvShowId: Long) {
        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val url = "https://api.themoviedb.org/3/tv/$tvShowId/videos?api_key=$apiKey"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val videos = response.getJSONArray("results")
                if (videos.length() > 0) {
                    val video = videos.getJSONObject(0)
                    val trailerKey = video.getString("key")
                    val trailerUrl = "https://www.youtube.com/watch?v=$trailerKey"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                    startActivity(intent)
                }
            },
            {
                Toast.makeText(this, "Error cargando el trailer del TV show", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }

    private fun getSeriesStreamingPlatforms(seriesId: Long) {
        rvPlataformas = findViewById(R.id.rvPlataformas)

        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val url = "https://api.themoviedb.org/3/tv/$seriesId/watch/providers?api_key=$apiKey"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val results = response.getJSONObject("results")
                    val platformsList = mutableListOf<Platform>()

                    val esPlatformsObject = results.optJSONObject("ES")
                    if (esPlatformsObject != null) {
                        val esPlatforms = esPlatformsObject.optJSONArray("flatrate")
                        if (esPlatforms != null) {
                            for (i in 0 until esPlatforms.length()) {
                                val platformObject = esPlatforms.getJSONObject(i)
                                val platformName = platformObject.getString("provider_name")
                                val platformLogoUrl = platformObject.getString("logo_path")
                                platformsList.add(Platform(platformName, platformLogoUrl))
                            }
                        } else {
                            // No se encontraron proveedores de transmisión
                            Toast.makeText(this, "No se encontraron proveedores de transmisión", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // No se encontraron proveedores de transmisión para España
                        Toast.makeText(this, "No se encontraron proveedores de transmisión para España", Toast.LENGTH_SHORT).show()
                    }

                    // Configura el adaptador y el layout manager para el RecyclerView
                    if (platformsList.isNotEmpty()) {
                        val adapter = PlatformAdapter(platformsList)
                        rvPlataformas.adapter = adapter
                        rvPlataformas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    }
                } catch (e: JSONException) {
                    // Error al analizar la respuesta JSON
                    Toast.makeText(this, "Error al analizar la respuesta JSON", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Error al cargar los proveedores de transmisión
                Toast.makeText(this, "Error cargando los proveedores de transmisión: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        // Agrega la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(request)
    }


    private fun getSimilarSeries(seriesId: Long, usuario: String,tipousuario:Int) {
        val rvSimilarSeries: RecyclerView = findViewById(R.id.rvPeliculasSimilares)

        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val language = "es"
        val url = "https://api.themoviedb.org/3/tv/$seriesId/similar?api_key=$apiKey&language=$language"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val results = response.getJSONArray("results")
                val seriesList = mutableListOf<TvShow>()

                for (i in 0 until results.length()) {
                    val tvShowObject = results.getJSONObject(i)
                    val tvshowIdd = tvShowObject.getLong("id")
                    val tvshowTitle = tvShowObject.optString("title", "")
                    val tvshowOverview = tvShowObject.optString("overview", "")
                    val tvshowPosterPath = tvShowObject.optString("poster_path", "")
                    val tvshowBackdropPath = tvShowObject.optString("backdrop_path", "")
                    val tvshowVoteAverage = tvShowObject.optDouble("vote_average", 0.0).toFloat()
                    val tvshowReleaseDate = tvShowObject.optString("release_date", "")
                    val tvshowOriginalLanguage = tvShowObject.optString("original_language", "")

                    val serie = TvShow(
                        tvshowIdd,
                        tvshowTitle,
                        tvshowOverview,
                        tvshowPosterPath,
                        tvshowBackdropPath,
                        tvshowVoteAverage,
                        tvshowReleaseDate,
                        tvshowOriginalLanguage
                    )
                    seriesList.add(serie)
                }

                if (seriesList.isNotEmpty()) {
                    val adapter = TvShowAdapter(seriesList) { serie ->
                        val intent = Intent(this@DetallesTvShow, DetallesTvShow::class.java)
                        intent.putExtra("extra_tv_show_id", serie.id)
                        intent.putExtra("extra_tv_show_title", serie.nombre)
                        intent.putExtra("extra_tv_show_overview", serie.overview)
                        intent.putExtra("extra_tv_show_poster", serie.posterPath)
                        intent.putExtra("extra_tv_show_backdrop", serie.backdropPath)
                        intent.putExtra("extra_tv_show_rating", serie.valoracion)
                        intent.putExtra("extra_tv_show_release_date", serie.fechaPiloto)
                        intent.putExtra("extra_tv_show_language", serie.idiomaOriginal)
                        intent.putExtra("usuario", usuario)
                        intent.putExtra("tipousuario", tipousuario)
                        startActivity(intent)
                    }
                    rvSimilarSeries.adapter = adapter
                    rvSimilarSeries.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                } else {
                    Toast.makeText(this, "No se encontraron series similares", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Toast.makeText(this, "Error cargando series similares", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }


}



