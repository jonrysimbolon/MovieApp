package com.jonrysimbolon.testskillmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepository
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val _category = MutableStateFlow<ResultStatus<List<CategoryModel>>>(ResultStatus.Loading)
    val category: StateFlow<ResultStatus<List<CategoryModel>>> get() = _category

    init {
        getAll()
    }

    private fun getAll(){
        viewModelScope.launch {
            _category.value = ResultStatus.Loading
            val responseData = categoryRepository.getAll()
            try {
                _category.value = ResultStatus.Success(responseData)
            }catch (e: Exception) {
                e.printStackTrace()
                _category.value = ResultStatus.Error(e.message.toString())
            }
        }
    }
}