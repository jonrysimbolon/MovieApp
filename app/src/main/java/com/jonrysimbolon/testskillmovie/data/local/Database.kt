package com.jonrysimbolon.testskillmovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jonrysimbolon.testskillmovie.data.local.dao.CategoryDao
import com.jonrysimbolon.testskillmovie.data.local.dao.MovieDao
import com.jonrysimbolon.testskillmovie.data.local.dao.RkmDao
import com.jonrysimbolon.testskillmovie.data.local.dao.RkurDao
import com.jonrysimbolon.testskillmovie.data.local.dao.UserReviewDao
import com.jonrysimbolon.testskillmovie.data.local.entity.CategoryEntity
import com.jonrysimbolon.testskillmovie.data.local.entity.MovieEntity
import com.jonrysimbolon.testskillmovie.data.local.entity.RkmEntity
import com.jonrysimbolon.testskillmovie.data.local.entity.RkurEntity
import com.jonrysimbolon.testskillmovie.data.local.entity.UserReviewEntity

@Database(
    entities = [
        CategoryEntity::class,
        MovieEntity::class,
        UserReviewEntity::class,
        RkurEntity::class,
        RkmEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun MovieDao(): MovieDao
    abstract fun RkmDao(): RkmDao
    abstract fun UserReviewDao(): UserReviewDao
    abstract fun RkurDao(): RkurDao
}