package com.jonrysimbolon.testskillmovie.data.remote.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CategoryResponses(
    @SerializedName("genres")
    val genres: List<CategoryModel>
)
data class CategoryModel(
    @SerializedName("id")
    @PrimaryKey val id: Int,
    @SerializedName("name")
    val name: String,
)