package com.jonrysimbolon.testskillmovie.data.remote.model


import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.base.model.BaseModel

data class MovieResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieModel> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)
data class MovieModel(
    @SerializedName("id")
    override val id: Int = 0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("title")
    val title: String = "",
): BaseModel<Int>