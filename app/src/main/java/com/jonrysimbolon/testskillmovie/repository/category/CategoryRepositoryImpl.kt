package com.jonrysimbolon.testskillmovie.repository.category

import androidx.room.withTransaction
import com.jonrysimbolon.testskillmovie.data.local.Database
import com.jonrysimbolon.testskillmovie.data.remote.ApiService

class CategoryRepositoryImpl constructor(
    private val remote: ApiService,
    private val local: Database
): CategoryRepository {
    override suspend fun getAllCategories(){
        val response = remote.getAllCategories()
        val data = response.genres
        local.withTransaction {
            local.categoryDao().apply {
                deleteAll()
                insertAllCategories(data)
            }
        }
    }
}