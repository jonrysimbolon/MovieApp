package com.jonrysimbolon.testskillmovie.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.jonrysimbolon.base.getLiveDataPager
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.repository.movie.pagingsource.MoviePagingSourceImpl

class MovieRepositoryImpl constructor(
    private val remote: ApiService,
) : MovieRepository {
    override suspend fun getAllPagingData(idCategory: String): LiveData<PagingData<MovieModel>> =
        getLiveDataPager(
            MoviePagingSourceImpl(
                remote,
                idCategory
            )
        )
}