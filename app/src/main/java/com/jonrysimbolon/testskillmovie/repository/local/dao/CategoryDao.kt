package com.jonrysimbolon.testskillmovie.repository.local.dao

import androidx.room.Query
import com.jonrysimbolon.testskillmovie.repository.local.model.CategoryModel

interface CategoryDao {
    @Query("SELECT * FROM category_tbl")
    fun getAllCategories(): List<CategoryModel>

    @Query("DELETE FROM category_tbl")
    suspend fun deleteAll()
}