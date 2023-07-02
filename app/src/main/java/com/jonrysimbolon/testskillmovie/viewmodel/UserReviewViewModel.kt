package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel
import com.jonrysimbolon.testskillmovie.repository.review.ReviewRepository
import kotlinx.coroutines.launch

class UserReviewViewModel constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private val _userReviewLivePaging: MutableLiveData<PagingData<UserReviewModel>> = MutableLiveData()
    val userReviewLivePaging: LiveData<PagingData<UserReviewModel>> get() = _userReviewLivePaging

    fun getAllReviews(movieId: Int) {
        viewModelScope.launch{
            reviewRepository.getAllPagingData(movieId)
                .cachedIn(viewModelScope)
                .observeForever { data ->
                    _userReviewLivePaging.value = data ?: PagingData.empty()
                }
        }
    }
}