package com.jonrysimbolon.testskillmovie.repository.trailer

import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.data.remote.model.VideoModel

class TrailerRepositoryImpl(
    private val remote: ApiService
): TrailerRepository {
    override suspend fun getAll(movieId: Int): List<VideoModel> = remote.getVideos(movieId).results
}