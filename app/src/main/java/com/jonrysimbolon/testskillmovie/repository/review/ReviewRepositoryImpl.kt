package com.jonrysimbolon.testskillmovie.repository.review

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel
import com.jonrysimbolon.testskillmovie.repository.review.pagingsource.ReviewPagingSourceImpl

class ReviewRepositoryImpl(
    private val remote: ApiService
): ReviewRepository {
    override suspend fun getAllPagingData(movieId: Int):LiveData<PagingData<UserReviewModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ReviewPagingSourceImpl(
                    remote,
                    movieId
                )
            },
        ).liveData
    }
}