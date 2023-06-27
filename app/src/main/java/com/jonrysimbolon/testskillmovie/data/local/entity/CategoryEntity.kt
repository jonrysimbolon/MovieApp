package com.jonrysimbolon.testskillmovie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.testskillmovie.utils.category_tbl

@Entity(tableName = category_tbl)
data class CategoryEntity(
    @SerializedName("id")
    @PrimaryKey val id: Int,
    @SerializedName("name")
    val name: String,
)