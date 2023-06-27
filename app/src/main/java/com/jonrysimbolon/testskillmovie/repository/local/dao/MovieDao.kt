package com.jonrysimbolon.testskillmovie.repository.local.dao

import androidx.room.Query

interface MovieDao {
    @Query("SELECT * FROM movie_tbl")
    fun getAllMovies(): List<MovieDao>

    @Query("DELETE FROM movie_tbl")
    suspend fun deleteAll()
}