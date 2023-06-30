package com.jonrysimbolon.testskillmovie.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel

interface MovieRepository {
    suspend fun getAllPagingData(idCategory: String): LiveData<PagingData<MovieModel>>
}