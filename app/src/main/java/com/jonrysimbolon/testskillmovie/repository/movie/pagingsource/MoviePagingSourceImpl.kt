package com.jonrysimbolon.testskillmovie.repository.movie.pagingsource

import com.jonrysimbolon.base.adapter.BasePagingSource
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel

class MoviePagingSourceImpl(
    private val remote: ApiService,
    private val idCategory: String,
) : BasePagingSource<Int, MovieModel>() {
    override suspend fun baseLoad(page: Int): List<MovieModel> =
        remote.getAllMovies(page, idCategory).results
}