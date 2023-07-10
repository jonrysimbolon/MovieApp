package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jonrysimbolon.testskillmovie.data.remote.model.DetailMovieModel
import com.jonrysimbolon.testskillmovie.repository.detailmovie.DetailMovieRepository
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import com.jonrysimbolon.testskillmovie.utils.responseGsonPattern
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMovieViewModel constructor(
    private val detailMovieRepository: DetailMovieRepository,
    private val gson: Gson,
) : ViewModel() {

    /**
     * Make plan to combine list trailer to this viewmodel
     *

        private val _trailer: MutableLiveData<ResultStatus<List<VideoModel>>> = MutableLiveData()
        val trailer: LiveData<ResultStatus<List<VideoModel>>> get() = _trailer

        fun getAllVideos(movieId: Int){
            viewModelScope.launch(Dispatchers.IO){
            _trailer.postValue(ResultStatus.Loading)
            val responseData = trailerRepository.getAll(movieId)
            try {
                _trailer.postValue(ResultStatus.Success(responseData))
            } catch (e: Exception) {
                e.printStackTrace()
                _trailer.postValue(ResultStatus.Error(e.message.toString()))
            }
        }

     */

    private val _detailMovie: MutableLiveData<ResultStatus<DetailMovieModel>> = MutableLiveData()
    val detailMovie: LiveData<ResultStatus<DetailMovieModel>> get() = _detailMovie

    fun getDetailMovie(movieId: Int){
        viewModelScope.launch(Dispatchers.IO){
            _detailMovie.postValue(ResultStatus.Loading)
            try {
                val detailMovieResponse = detailMovieRepository.getAll(movieId)
                val responseBody = detailMovieResponse.body()
                if (detailMovieResponse.isSuccessful && responseBody != null) {
                    _detailMovie.postValue(ResultStatus.Success(responseBody))
                } else {
                    _detailMovie.postValue(
                        ResultStatus.Error(
                            responseGsonPattern(
                                gson,
                                detailMovieResponse
                                    .errorBody()
                                    ?.string()
                                    .toString()
                            ).statusMessage
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _detailMovie.postValue(ResultStatus.Error(e.message.toString()))
            }
        }
    }
}