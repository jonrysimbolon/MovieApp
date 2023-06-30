package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.repository.movie.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieLivePaging: MutableLiveData<PagingData<MovieModel>> = MutableLiveData()
    val movieLivePaging: LiveData<PagingData<MovieModel>> get() = _movieLivePaging

    fun getAllMovies(categoryId: String) {
        viewModelScope.launch {
            movieRepository.getAllPagingData(categoryId)
                .cachedIn(viewModelScope)
                .observeForever { data ->
                    _movieLivePaging.value = data ?: PagingData.empty()
                }
        }
    }
}