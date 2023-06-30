package com.jonrysimbolon.testskillmovie.repository.detailmovie

import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.DetailMovieModel

class DetailMovieRepositoryImpl(
    private var remote: ApiService
) : DetailMovieRepository {
    override suspend fun getAll(movieId: Int): retrofit2.Response<DetailMovieModel> =
        remote.getMovie(movieId)
}