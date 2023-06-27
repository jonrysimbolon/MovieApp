package com.jonrysimbolon.testskillmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonrysimbolon.testskillmovie.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(stories: List<CategoryEntity>)

    @Query("SELECT * FROM category_tbl ORDER BY name ASC")
    fun getAllCategories(): List<CategoryEntity>

    @Query("DELETE FROM category_tbl")
    suspend fun deleteAll()
}