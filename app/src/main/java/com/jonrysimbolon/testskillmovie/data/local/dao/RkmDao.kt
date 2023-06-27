package com.jonrysimbolon.testskillmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonrysimbolon.testskillmovie.data.local.entity.RkmEntity

@Dao
interface RkmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RkmEntity>)

    @Query("SELECT * FROM remote_keys_movie_tbl WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RkmEntity?

    @Query("DELETE FROM remote_keys_movie_tbl")
    suspend fun deleteRemoteKeys()
}