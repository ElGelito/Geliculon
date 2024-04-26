package com.example.geliculon

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

object TvShowRepository {

    private val api:Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }
    fun getPopularTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getUpcomingTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getUpcomingTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()

                }
            })
    }


    fun getTopRatedTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getTopRatedTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()

                }
            })
    }


    fun getairingtodayTvShow(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getairingtodayTvShow(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getComedyTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getComedyTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getActionTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getActionTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }



    fun getDramaTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getDramaTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getHorrorTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getHorrorTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getSpanishTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getSpanishTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getAnimationTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getAnimationTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getFamilyTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getFamilyTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getSciFiTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getSciFiTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getFantasyTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getFantasyTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getDocumentaryTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getDocumentaryTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getWesternTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getWesternTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getWarTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getWarTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getSportsTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getSportsTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }




}