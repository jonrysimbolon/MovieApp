package com.jonrysimbolon.testskillmovie.repository.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonrysimbolon.testskillmovie.repository.local.model.RkmModel

interface RkmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RkmModel>)

    @Query("SELECT * FROM remote_keys_movie_tbl WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RkmModel?

    @Query("DELETE FROM remote_keys_movie_tbl")
    suspend fun deleteRemoteKeys()
}