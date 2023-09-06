package com.jonrysimbolon.testskillmovie.data.remote.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.base.model.BaseModel

data class CategoryResponses(
    @SerializedName("genres")
    val genres: List<CategoryModel> = listOf()
)
data class CategoryModel(
    @SerializedName("id")
    @PrimaryKey override val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
): BaseModel<Int>