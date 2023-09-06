package com.jonrysimbolon.testskillmovie.data.remote.model


import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.base.model.BaseModel

data class VideoResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("results")
    val results: List<VideoModel> = listOf()
)

data class VideoModel(
    @SerializedName("id")
    override val id: String = "",
    @SerializedName("key")
    val key: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("published_at")
    val publishedAt: String = ""
): BaseModel<String>