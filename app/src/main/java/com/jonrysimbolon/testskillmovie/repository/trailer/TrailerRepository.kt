package com.jonrysimbolon.testskillmovie.repository.trailer

import com.jonrysimbolon.testskillmovie.data.remote.model.VideoModel

interface TrailerRepository {
    suspend fun getAll(movieId: Int): List<VideoModel>
}