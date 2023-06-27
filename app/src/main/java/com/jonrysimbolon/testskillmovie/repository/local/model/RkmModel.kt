package com.jonrysimbolon.testskillmovie.repository.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonrysimbolon.testskillmovie.utils.rkm_tbl

@Entity(tableName = rkm_tbl)
data class RkmModel(
    @PrimaryKey val id: String,
)
