package com.example.geliculon
import com.google.gson.annotations.SerializedName

data class Pelicula(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val titulo: String,
    @SerializedName("overview") val descripcionGeneral: String,
    @SerializedName("poster_path") val rutaPoster: String,
    @SerializedName("backdrop_path") val rutaBackdrop: String,
    @SerializedName("vote_average") val valoracion: Float,
    @SerializedName("release_date") val fechaEstreno: String,
    @SerializedName("original_language") val idiomaOriginal:String,

)
