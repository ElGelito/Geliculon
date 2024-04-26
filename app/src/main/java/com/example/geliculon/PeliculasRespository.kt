package com.example.geliculon

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

object PeliculasRespository {
    private val api: Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun getPopularMovies(
        page: Int = 1,
        onSuccess:(movies:List<Pelicula>)->Unit,
        onError:()->Unit
     ) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    }else{
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getUpcomingMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getUpcomingMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getTopRatedMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getTopRatedMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getNowPlaying(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ){
        api.getNowPlaying(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getComedyMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getComedyMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getactionMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getActionMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getDramaMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getDramaMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getTerrorMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getHorrorMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getSpanishMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getSpanishMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }



    fun getAnimatedMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getAnimatedMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getFamilyMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getFamilyMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getSciFiMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getSciFiMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getFantasyMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getFantasyMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getDocumentaryMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getDocumentaryMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getWesternMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getWesternMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getWarMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getWarMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getSportsMovies(
        page: Int = 1,
        onSuccess: (movies: List<Pelicula>) -> Unit,
        onError: () -> Unit
    ) {
        api.getSportsMovies(page = page)
            .enqueue(object : Callback<GetPeliculasResponse> {
                override fun onResponse(
                    call: Call<GetPeliculasResponse>,
                    response: Response<GetPeliculasResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.peliculas)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetPeliculasResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


}



