package com.jonrysimbolon.testskillmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jonrysimbolon.testskillmovie.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_tbl")
    fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM movie_tbl")
    suspend fun deleteAll()
}