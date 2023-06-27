package com.jonrysimbolon.testskillmovie.data.remote.model


import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.testskillmovie.data.local.entity.MovieEntity

data class MovieModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieEntity>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)