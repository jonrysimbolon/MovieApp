package com.jonrysimbolon.testskillmovie.repository.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonrysimbolon.testskillmovie.utils.rkur_tbl

@Entity(tableName = rkur_tbl)
data class RkurModel(
    @PrimaryKey val id: String,
)
