package com.jonrysimbolon.testskillmovie.repository.category

import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel

interface CategoryRepository {
    suspend fun getAll(): List<CategoryModel>
}