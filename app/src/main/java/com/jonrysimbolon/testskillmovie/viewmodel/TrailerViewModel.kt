package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonrysimbolon.testskillmovie.data.remote.model.VideoModel
import com.jonrysimbolon.testskillmovie.repository.trailer.TrailerRepository
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrailerViewModel constructor(
    private val trailerRepository: TrailerRepository
        ): ViewModel() {

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
    }
}