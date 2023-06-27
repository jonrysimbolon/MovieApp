package com.jonrysimbolon.testskillmovie.repository.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonrysimbolon.testskillmovie.repository.local.model.RkurModel

interface RkurDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RkurModel>)

    @Query("SELECT * FROM remote_keys_user_review_tbl WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RkurModel?

    @Query("DELETE FROM remote_keys_user_review_tbl")
    suspend fun deleteRemoteKeys()
}