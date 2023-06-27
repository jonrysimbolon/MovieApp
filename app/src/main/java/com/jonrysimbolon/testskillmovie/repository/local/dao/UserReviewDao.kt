package com.jonrysimbolon.testskillmovie.repository.local.dao

import androidx.room.Query

interface UserReviewDao {
    @Query("SELECT * FROM user_review_tbl")
    fun getAllUserReviews(): List<UserReviewDao>

    @Query("DELETE FROM user_review_tbl")
    suspend fun deleteAll()
}