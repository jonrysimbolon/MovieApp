package com.jonrysimbolon.testskillmovie.repository.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonrysimbolon.testskillmovie.utils.category_tbl

@Entity(tableName = category_tbl)
data class CategoryModel(
    @PrimaryKey val id: Int,
    val name: String,
)