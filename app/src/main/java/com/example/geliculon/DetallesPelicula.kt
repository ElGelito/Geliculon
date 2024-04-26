package com.example.geliculon


import okhttp3.*

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL

const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"
const val MOVIE_LANGUAGE="extra_movie_language"
const val MOVIE_ID = "extra_movie_id"


class DetallesPelicula : AppCompatActivity() {
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var titulo: TextView
    private lateinit var valoracion: RatingBar
    private lateinit var fechaEstreno: TextView
    private lateinit var overview: TextView
    private lateinit var idiomaOriginal: TextView
    private lateinit var addToWatchList: Button

    private lateinit var actorAdapter: ActorAdapter
    private lateinit var rvActors: RecyclerView

    private var movieId = 0L
    private var movieBackdrop = ""
    private var moviePoster = ""
    private var movieTitle = ""
    private var movieRating = 0f
    private var movieReleaseDate = ""
    private var movieOverview = ""
    private var movieoriginallanguage=""


    private lateinit var rvPlataformas: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_pelicula)
         var tipousuario=intent.getIntExtra("tipousuario",0)
        backdrop = findViewById(R.id.movie_backdrop)
        poster = findViewById(R.id.movie_poster)
        titulo = findViewById(R.id.movie_titlee)
        valoracion = findViewById(R.id.movie_rating)
        fechaEstreno = findViewById(R.id.movie_release_date)
        overview = findViewById(R.id.movie_overview)
        idiomaOriginal=findViewById(R.id.phIdioma)
        addToWatchList = findViewById(R.id.add_to_watch_list)

        val extras = intent.extras
        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }

        var trailer:Button = findViewById(R.id.trailer)
        val idvideo = extras!!.getLong(MOVIE_ID)
        trailer.setOnClickListener{

                loadMovieTrailer(idvideo)
        }

        var botonresenias:Button=findViewById(R.id.button8)

        botonresenias.setOnClickListener {
            if (tipousuario==2) {
                Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
            }else{
                val intent=Intent(this,Resenias::class.java)
                intent.putExtra("movieid",idvideo.toInt())
                intent.putExtra("movietitle",movieTitle)
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
        rvActors.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        rvActors.adapter = actorAdapter

        // Obtiene los actores utilizando la API de TMDB
        val apiKey = "54e87eee285a99614f18a228f764e2ea"

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tmdbService = retrofit.create(Api::class.java)
        val call = tmdbService.getMovieCredits(movieId.toString(), apiKey)

        call.enqueue(object : Callback<MovieCreditsResponse> {
            override fun onResponse(
                call: Call<MovieCreditsResponse>,
                response: Response<MovieCreditsResponse>
            ) {
                if (response.isSuccessful) {
                    val credits = response.body()?.cast ?: emptyList()
                    actorAdapter.updateActors(credits)
                } else {
                    // Manejo de errores
                }
            }

            override fun onFailure(call: Call<MovieCreditsResponse>, t: Throwable) {
                // Manejo de errores
            }
        })


        getMovieStreamingPlatforms(movieId)
        getSimilarMovies(movieId,intent.getStringExtra("usuario").toString(),tipousuario)




        val botonusuarioreseñas = findViewById<Button>(R.id.reviews_button)
        botonusuarioreseñas.setOnClickListener {
            if (tipousuario == 2) {
                Toast.makeText(this, "Inicia Sesión Primero", Toast.LENGTH_LONG).show()
            } else {
                showReviewDialog(intent.getStringExtra("usuario").toString())
            }
        }


    }

    data class Review(
        val userId: String = "",
        val userName: String? = "",
        val id: Long = 0L,
        val title: String? = "titulo",
        val rating: Float = 0f,
        val reviewText: String = "",
        val tipoReseña: String = ""
    )




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

    private fun saveReview(dialogView: View,usuario: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val userName = usuario
            val ratingBar = dialogView.findViewById<RatingBar>(R.id.dialog_rating_bar)
            val reviewText = dialogView.findViewById<EditText>(R.id.dialog_review_text).text.toString()

            // Obtener los datos de la película
            val movieId = intent.getLongExtra(MOVIE_ID, 0L)
            val movieTitle = intent.getStringExtra(MOVIE_TITLE)

            // Crear un objeto Review
            val review = Review(userId, userName, movieId, movieTitle, ratingBar.rating, reviewText,"Pelicula")

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
        movieId = extras.getLong(MOVIE_ID)
        movieBackdrop = extras.getString(MOVIE_BACKDROP, "")
        moviePoster = extras.getString(MOVIE_POSTER, "")
        movieTitle = extras.getString(MOVIE_TITLE, "")
        movieRating = extras.getFloat(MOVIE_RATING, 0f)
        movieReleaseDate = extras.getString(MOVIE_RELEASE_DATE, "")
        movieOverview = extras.getString(MOVIE_OVERVIEW, "")
        movieoriginallanguage=extras.getString(MOVIE_LANGUAGE,"")
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280$movieBackdrop")
            .transform(CenterCrop())
            .into(backdrop)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342$moviePoster")
            .transform(CenterCrop())
            .into(poster)
        titulo.text = movieTitle
        valoracion.rating = movieRating / 2
        fechaEstreno.text = movieReleaseDate
        overview.text = movieOverview
        idiomaOriginal.text=movieoriginallanguage
        val movie = getMovie(movieId)
        if (movie == null) {
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

    private fun getMovie(id: Long): MovieEntity? {
        return db.movieDao().findById(id)
    }
    override fun onStart() {
        super.onStart()
        addToWatchList.setOnClickListener {
            if(intent.getIntExtra("tipousuario",0)==2){
                Toast.makeText(this,"Inicia Sesion Primero",Toast.LENGTH_LONG).show()
            }else{
                if (getMovie(movieId) == null) {
                    val entity = MovieEntity(
                        movieId,
                        movieTitle,
                        movieOverview,
                        moviePoster,
                        movieBackdrop,
                        movieRating,
                        movieReleaseDate,
                        movieoriginallanguage
                    )
                    db.movieDao().insert(entity)
                    addToWatchList.text = getString(R.string.remove_from_watch_list)
                } else {
                    db.movieDao().delete(movieId)
                    addToWatchList.text = getString(R.string.add_to_watch_list)
                }
            }


        }
    }

    private fun loadMovieTrailer(movieId: Long) {
        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val url = "https://api.themoviedb.org/3/movie/$movieId/videos?api_key=$apiKey"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val videos = it.getJSONArray("results")
                if (videos.length() > 0) {
                    val video = videos.getJSONObject(0)
                    val trailerKey = video.getString("key")
                    val trailerUrl = "https://www.youtube.com/watch?v=$trailerKey"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                    startActivity(intent)
                }
            },
            {
                Toast.makeText(this, "Error cargando el trailer de la película", Toast.LENGTH_SHORT)
                    .show()
            })

        Volley.newRequestQueue(this).add(request)
    }


    private fun getMovieStreamingPlatforms(movieId: Long) {
        rvPlataformas = findViewById(R.id.rvPlataformas)

        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val url = "https://api.themoviedb.org/3/movie/$movieId/watch/providers?api_key=$apiKey&language=es"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
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

                        if (platformsList.isNotEmpty()) {
                            val adapter = PlatformAdapter(platformsList)
                            rvPlataformas.adapter = adapter
                            rvPlataformas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        } else {
                            Toast.makeText(this, "No se encontraron proveedores de transmisión", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "No se encontraron proveedores de transmisión para España", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "No se encontraron proveedores de transmisión para España", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Toast.makeText(this, "Error cargando los proveedores de transmisión", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }

        private fun getSimilarMovies(movieId: Long, usuario: String,tipousuario:Int) {
        val rvSimilarMovies: RecyclerView = findViewById(R.id.rvPeliculasSimilares)

        val apiKey = "54e87eee285a99614f18a228f764e2ea"
        val language = "es"
        val url = "https://api.themoviedb.org/3/movie/$movieId/similar?api_key=$apiKey&language=$language"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val results = response.getJSONArray("results")
                val moviesList = mutableListOf<Pelicula>()

                for (i in 0 until results.length()) {
                    val movieObject = results.getJSONObject(i)
                    val movieIdd = movieObject.getLong("id")
                    val movieTitle = movieObject.getString("title")
                    val movieOverview = movieObject.getString("overview")
                    val moviePosterPath = movieObject.getString("poster_path")
                    val movieBackdropPath = movieObject.getString("backdrop_path")
                    val movieVoteAverage = movieObject.getDouble("vote_average").toFloat()
                    val movieReleaseDate = movieObject.getString("release_date")
                    val movieOriginalLanguage = movieObject.getString("original_language")

                    val movie = Pelicula(
                        movieIdd,
                        movieTitle,
                        movieOverview,
                        moviePosterPath,
                        movieBackdropPath,
                        movieVoteAverage,
                        movieReleaseDate,
                        movieOriginalLanguage
                    )
                    moviesList.add(movie)
                }

                if (moviesList.isNotEmpty()) {
                    val adapter = PeliculasAdapter(moviesList) { movie ->
                        val intent = Intent(this@DetallesPelicula, DetallesPelicula::class.java)
                        intent.putExtra("extra_movie_id", movie.id)
                        intent.putExtra("extra_movie_title", movie.titulo)
                        intent.putExtra("extra_movie_overview", movie.descripcionGeneral)
                        intent.putExtra("extra_movie_poster", movie.rutaPoster)
                        intent.putExtra("extra_movie_backdrop", movie.rutaBackdrop)
                        intent.putExtra("extra_movie_rating", movie.valoracion)
                        intent.putExtra("extra_movie_release_date", movie.fechaEstreno)
                        intent.putExtra("extra_movie_language", movie.idiomaOriginal)
                        intent.putExtra("usuario", usuario)
                        intent.putExtra("tipousuario", tipousuario)
                        startActivity(intent)
                    }
                    rvSimilarMovies.adapter = adapter
                    rvSimilarMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                } else {
                    Toast.makeText(this, "No se encontraron películas similares", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Toast.makeText(this, "Error cargando películas similares", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(request)
    }


}


data class Platform(val name: String, val logoUrl: String)
class PlatformAdapter(private val platforms: List<Platform>) :
    RecyclerView.Adapter<PlatformAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_platform, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val platform = platforms[position]
        holder.platformName.text = platform.name
        val baseUrl = "https://image.tmdb.org/t/p/w92"
        val logoUrl = "$baseUrl${platform.logoUrl}"
        Glide.with(holder.itemView).load(logoUrl).into(holder.platformLogo)
    }

    override fun getItemCount(): Int {
        return platforms.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val platformName: TextView = itemView.findViewById(R.id.tvPlatformName)
        val platformLogo: ImageView = itemView.findViewById(R.id.ivPlatformLogo)
    }
}



