package com.jonrysimbolon.testskillmovie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonrysimbolon.testskillmovie.utils.rkm_tbl

@Entity(tableName = rkm_tbl)
data class RkmEntity(
    @PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)
