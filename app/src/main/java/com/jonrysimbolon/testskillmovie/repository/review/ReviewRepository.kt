package com.jonrysimbolon.testskillmovie.repository.review

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel

interface ReviewRepository {
    suspend fun getAllPagingData(movieId: Int): LiveData<PagingData<UserReviewModel>>
}