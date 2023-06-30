package com.jonrysimbolon.testskillmovie.data.remote

import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryResponses
import com.jonrysimbolon.testskillmovie.data.remote.model.DetailMovieModel
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieResponse
import com.jonrysimbolon.testskillmovie.data.remote.model.ReviewResponses
import com.jonrysimbolon.testskillmovie.data.remote.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getAllCategories(): CategoryResponses

    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("with_genres") with_genre: String,
    ): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
    ): retrofit2.Response<DetailMovieModel>

    @GET("movie/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: Int,
        @Query("page") page: Int,
    ): ReviewResponses

    @GET("movie/{id}/videos")
    suspend fun getVideos(
        @Path("id") id: Int,
    ): VideoResponse

}