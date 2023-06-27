package com.jonrysimbolon.testskillmovie.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jonrysimbolon.testskillmovie.repository.local.dao.CategoryDao
import com.jonrysimbolon.testskillmovie.repository.local.dao.MovieDao
import com.jonrysimbolon.testskillmovie.repository.local.dao.RkmDao
import com.jonrysimbolon.testskillmovie.repository.local.dao.RkurDao
import com.jonrysimbolon.testskillmovie.repository.local.dao.UserReviewDao
import com.jonrysimbolon.testskillmovie.repository.local.model.CategoryModel
import com.jonrysimbolon.testskillmovie.repository.local.model.MovieModel
import com.jonrysimbolon.testskillmovie.repository.local.model.RkmModel
import com.jonrysimbolon.testskillmovie.repository.local.model.RkurModel
import com.jonrysimbolon.testskillmovie.repository.local.model.UserReviewModel

@Database(
    entities = [
        CategoryModel::class,
        MovieModel::class,
        UserReviewModel::class,
        RkurModel::class,
        RkmModel::class,
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