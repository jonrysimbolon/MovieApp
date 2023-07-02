package com.jonrysimbolon.testskillmovie.repository.movie.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel

class MoviePagingSourceImpl(
    private val remote: ApiService,
    private val idCategory: String,
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = remote.getAllMovies(page, idCategory).results
            LoadResult.Page(
                data = responseData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}