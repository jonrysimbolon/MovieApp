package com.jonrysimbolon.testskillmovie.data.remote.model


import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.testskillmovie.data.local.entity.CategoryEntity

data class CategoryModel(
    @SerializedName("genres")
    val genres: List<CategoryEntity>
)