package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepository
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import kotlinx.coroutines.launch

class CategoryViewModel constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val _category: MutableLiveData<ResultStatus<List<CategoryModel>>> = MutableLiveData()
    val category: LiveData<ResultStatus<List<CategoryModel>>> get() = _category

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            _category.postValue(ResultStatus.Loading)
            val responseData = categoryRepository.getAll()
            try {
                _category.postValue(ResultStatus.Success(responseData))
            } catch (e: Exception) {
                e.printStackTrace()
                _category.postValue(ResultStatus.Error(e.message.toString()))
            }
        }
    }
}