package com.jonrysimbolon.testskillmovie.ui.splash

import androidx.lifecycle.ViewModel
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepository

class SplashViewModel constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    suspend fun getAllCategories(): Boolean = try {
        categoryRepository.getAllCategories()
        true
    } catch (e: Exception) {
        false
    }
}