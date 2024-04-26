package com.example.geliculon
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface Api {


/*PELICULAS*//////////////////////////////////////////////////////////////////////////

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",

        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>


    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>


    @GET("discover/movie")
    fun getComedyMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "35",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getActionMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "28",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getDramaMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "18",
        @Query("sort_by") sortBy: String = "vote_count.desc",

        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getHorrorMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "27",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getSpanishMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_original_language") language: String = "es",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") languagee: String = "es-ES"
    ): Call<GetPeliculasResponse>



    @GET("discover/movie")
    fun getAnimatedMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "16",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getFamilyMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "10751",
        @Query("without_genres") withoutGenres: String = "16",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>


    @GET("discover/movie")
    fun getSciFiMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "878",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>


    @GET("discover/movie")
    fun getFantasyMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("without_genres") withoutGenres: String = "16",
        @Query("with_genres") genres: String = "14",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getDocumentaryMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "99",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getWesternMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "37",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

    @GET("discover/movie")
    fun getWarMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "10752",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>


    @GET("discover/movie")
    fun getSportsMovies(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("with_genres") genres: String = "18",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES"
    ): Call<GetPeliculasResponse>

/*PELICULAS*//////////////////////////////////////////////////////////////////////////



/*SERIES*//////////////////////////////////////////////////////////////////////////
@GET("tv/popular")
fun getPopularTvShows(
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
    @Query("page") page: Int,
    @Query("language") language: String = "es-ES",
    @Query("with_genres") withGenress: String = "10765",

): Call<GetTvShowResponse>


@GET("tv/on_the_air")
fun getUpcomingTvShows(
    @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
    @Query("page") page: Int,
    @Query("language") language: String = "es-ES",
    @Query("air_date.gte") airDate: String = getCurrentDate(),
    @Query("with_genres") withGenress: String = "10765",
): Call<GetTvShowResponse>
private fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, 1) // Agrega 1 día para obtener solo series futuras
    return dateFormat.format(calendar.time)
}


@GET("tv/top_rated")
fun getTopRatedTvShows(

    @Query("api_key") apiKey: String = "c9c5ea53799624204822e99e30c87b54",
    @Query("page") page: Int,
    @Query("language") language: String = "es-ES",
): Call<GetTvShowResponse>

@GET("tv/airing_today")
fun getairingtodayTvShow(
    @Query("api_key") apiKey: String = "c9c5ea53799624204822e99e30c87b54",
    @Query("page") page: Int,
    @Query("language") language: String = "es-ES",
):Call<GetTvShowResponse>



@GET("tv/popular")
fun getComedyTvShows(
    @Query("api_key") apiKey: String="c9c5ea53799624204822e99e30c87b54",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("page") page: Int,
    @Query("language") language: String = "es-ES",
    @Query("with_genres") genres: String = "35"
): Call<GetTvShowResponse>


@GET("discover/tv")
fun getActionTvShows(
    @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
    @Query("page") page: Int,
    @Query("language") language: String = "es-ES",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("with_genres") genres: String = "80"
): Call<GetTvShowResponse>

@GET("discover/tv")
fun getDramaTvShows(
    @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
    @Query("page") page: Int,
    @Query("language") language: String = "es-ES",
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("with_genres") genres: String = "18",

): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getHorrorTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "9648"
    ): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getSpanishTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("region") region: String = "ES",
        @Query("with_genres") genres: String = "80",
        @Query("with_original_language") originalLanguage: String = "es"
    ): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getAnimationTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "16"
    ): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getFamilyTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("without_genres") withoutGenres: String = "16",
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "10751"
    ): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getSciFiTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genres: String = "10765"
    ): Call<GetTvShowResponse>


    @GET("discover/tv")
    fun getFantasyTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "9648"
    ): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getDocumentaryTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "99"
    ): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getWesternTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "37"
    ): Call<GetTvShowResponse>

    @GET("discover/tv")
    fun getWarTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "10768" // ID del género de guerra
    ): Call<GetTvShowResponse>


    @GET("discover/tv")
    fun getSportsTvShows(
        @Query("api_key") apiKey: String = "54e87eee285a99614f18a228f764e2ea",
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String = "99"
    ): Call<GetTvShowResponse>



/*SERIES*//////////////////////////////////////////////////////////////////////////

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<ReviewResponse>

    @GET("tv/{tv_id}/reviews")
    fun getSeriesReviews(
        @Path("tv_id") seriesId: Int,
        @Query("api_key") apiKey: String
    ): Call<ReviewResponse2>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
    ): Call<MovieCreditsResponse>

    @GET("person/{person_id}")
    fun getActorDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
    ): Call<ActorDetailsResponse>

    @GET("tv/{tv_id}/credits")
    fun getTVShowCredits(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Call<TVShowCreditsResponse>

}

data class ActorDetailsResponse(
    val id: Int,
    val name: String,
    val biography: String,
    @SerializedName("profile_path")
    val profilePath: String?
)
data class MovieCreditsResponse(
    val id: Int,
    val cast: List<Actor>
)

data class TVShowCreditsResponse(
    val id: Int,
    val cast: List<Actor>
)

data class Actor(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?
)