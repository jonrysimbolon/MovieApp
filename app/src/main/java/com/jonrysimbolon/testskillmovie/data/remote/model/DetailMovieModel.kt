package com.jonrysimbolon.testskillmovie.data.remote.model


import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.base.model.BaseModel

data class DetailMovieModel(
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("id")
    override val id: Int = 0,
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("title")
    val title: String = ""
): BaseModel<Int>