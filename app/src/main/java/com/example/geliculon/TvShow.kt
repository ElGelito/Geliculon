package com.example.geliculon

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val nombre: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val valoracion: Float,
    @SerializedName("first_air_date") val fechaPiloto: String,
    @SerializedName("original_language") val idiomaOriginal:String,
)
