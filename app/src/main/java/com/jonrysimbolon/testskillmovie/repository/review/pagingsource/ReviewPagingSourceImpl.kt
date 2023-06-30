package com.jonrysimbolon.testskillmovie.repository.review.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel

class ReviewPagingSourceImpl(
    private val remote: ApiService,
    private val movieId: Int,
) : PagingSource<Int, UserReviewModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserReviewModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserReviewModel> {
        return try {
                val page = params.key ?: INITIAL_PAGE_INDEX
                val responseData = remote.getReviews(movieId, page).results
                LoadResult.Page(
                    data = responseData,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (responseData.isEmpty()) null else page + 1
                )
        } catch (exception: Exception) {
            println("error: ${exception.printStackTrace()}")
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}