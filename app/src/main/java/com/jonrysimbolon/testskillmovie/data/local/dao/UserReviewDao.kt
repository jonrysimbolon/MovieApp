package com.jonrysimbolon.testskillmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jonrysimbolon.testskillmovie.data.local.entity.UserReviewEntity

@Dao
interface UserReviewDao {
    @Query("SELECT * FROM user_review_tbl")
    fun getAllUserReviews(): List<UserReviewEntity>

    @Query("DELETE FROM user_review_tbl")
    suspend fun deleteAll()
}