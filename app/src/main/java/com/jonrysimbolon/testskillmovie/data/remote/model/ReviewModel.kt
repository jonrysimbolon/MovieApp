package com.jonrysimbolon.testskillmovie.data.remote.model


import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.testskillmovie.data.local.entity.UserReviewEntity

data class ReviewModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<UserReviewEntity>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)