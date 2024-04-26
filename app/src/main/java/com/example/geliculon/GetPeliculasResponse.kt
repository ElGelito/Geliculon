package com.example.geliculon
import com.google.gson.annotations.SerializedName

data class GetPeliculasResponse(
    @SerializedName("page") val pagina: Int,
    @SerializedName("results") val peliculas: List<Pelicula>,
    @SerializedName("total_pages") val paginas: Int
)
