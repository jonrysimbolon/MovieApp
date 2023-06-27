package com.jonrysimbolon.testskillmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonrysimbolon.testskillmovie.data.local.entity.RkurEntity

@Dao
interface RkurDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RkurEntity>)

    @Query("SELECT * FROM remote_keys_user_review_tbl WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RkurEntity?

    @Query("DELETE FROM remote_keys_user_review_tbl")
    suspend fun deleteRemoteKeys()
}