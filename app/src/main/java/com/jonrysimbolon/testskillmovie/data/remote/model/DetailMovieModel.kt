package com.jonrysimbolon.testskillmovie.data.remote.model


import com.google.gson.annotations.SerializedName

data class DetailMovieModel(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String
)