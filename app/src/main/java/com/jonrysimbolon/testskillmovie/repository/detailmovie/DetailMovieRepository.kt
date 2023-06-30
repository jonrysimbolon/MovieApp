package com.jonrysimbolon.testskillmovie.repository.detailmovie

import com.jonrysimbolon.testskillmovie.data.remote.model.DetailMovieModel

interface DetailMovieRepository {
    suspend fun getAll(movieId: Int): retrofit2.Response<DetailMovieModel>
}