package com.jonrysimbolon.testskillmovie.data.remote

import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel
import com.jonrysimbolon.testskillmovie.data.remote.model.DetailMovieModel
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.data.remote.model.ReviewModel
import com.jonrysimbolon.testskillmovie.utils.videos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getAllCategories(): CategoryModel

    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("with_genres") with_genre: String,
    ): MovieModel

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("append_to_response") appendToResponse: String = videos,
    ): DetailMovieModel

    @GET("movie/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: Int,
        @Query("page") page: Int,
    ): ReviewModel


}