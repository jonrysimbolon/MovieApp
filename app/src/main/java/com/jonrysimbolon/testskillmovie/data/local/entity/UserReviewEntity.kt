package com.jonrysimbolon.testskillmovie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jonrysimbolon.testskillmovie.utils.user_review_tbl

@Entity(tableName = user_review_tbl)
data class UserReviewEntity(
    @PrimaryKey val id: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)
