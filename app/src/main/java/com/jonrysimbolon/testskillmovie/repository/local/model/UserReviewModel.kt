package com.jonrysimbolon.testskillmovie.repository.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonrysimbolon.testskillmovie.utils.user_review_tbl

@Entity(tableName = user_review_tbl)
data class UserReviewModel(
    @PrimaryKey val id: Int,
)
