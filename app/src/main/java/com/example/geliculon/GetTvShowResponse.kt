package com.example.geliculon

import com.google.gson.annotations.SerializedName

data class GetTvShowResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvShows: List<TvShow>,
    @SerializedName("total_pages") val pages: Int
)
