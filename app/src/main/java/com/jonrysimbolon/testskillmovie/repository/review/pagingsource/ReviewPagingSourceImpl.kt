package com.jonrysimbolon.testskillmovie.repository.review.pagingsource

import com.jonrysimbolon.base.adapter.BasePagingSource
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel

class ReviewPagingSourceImpl(
    private val remote: ApiService,
    private val movieId: Int,
) : BasePagingSource<String, UserReviewModel>() {
    override suspend fun baseLoad(page: Int): List<UserReviewModel> =
        remote.getReviews(movieId, page).results
}