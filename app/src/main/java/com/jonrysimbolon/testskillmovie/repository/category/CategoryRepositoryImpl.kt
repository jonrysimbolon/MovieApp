package com.jonrysimbolon.testskillmovie.repository.category

import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel

class CategoryRepositoryImpl constructor(
    private val remote: ApiService,
): CategoryRepository {
    override suspend fun getAll(): List<CategoryModel> = remote.getAllCategories().genres
}