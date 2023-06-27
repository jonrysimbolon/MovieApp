package com.jonrysimbolon.testskillmovie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonrysimbolon.testskillmovie.utils.rkur_tbl

@Entity(tableName = rkur_tbl)
data class RkurEntity(
    @PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)
