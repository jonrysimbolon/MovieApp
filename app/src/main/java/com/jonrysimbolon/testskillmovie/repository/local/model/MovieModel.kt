package com.jonrysimbolon.testskillmovie.repository.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonrysimbolon.testskillmovie.utils.movie_tbl

@Entity(tableName = movie_tbl)
data class MovieModel(
    @PrimaryKey val id: Int,
)