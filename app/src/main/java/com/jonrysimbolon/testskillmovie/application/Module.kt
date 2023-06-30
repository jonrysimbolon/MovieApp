package com.jonrysimbolon.testskillmovie.application

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.jonrysimbolon.testskillmovie.BuildConfig
import com.jonrysimbolon.testskillmovie.adapter.CategoryAdapter
import com.jonrysimbolon.testskillmovie.adapter.MovieAdapter
import com.jonrysimbolon.testskillmovie.adapter.TrailerAdapter
import com.jonrysimbolon.testskillmovie.adapter.UserReviewAdapter
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepository
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepositoryImpl
import com.jonrysimbolon.testskillmovie.repository.detailmovie.DetailMovieRepository
import com.jonrysimbolon.testskillmovie.repository.detailmovie.DetailMovieRepositoryImpl
import com.jonrysimbolon.testskillmovie.repository.movie.MovieRepository
import com.jonrysimbolon.testskillmovie.repository.movie.MovieRepositoryImpl
import com.jonrysimbolon.testskillmovie.repository.review.ReviewRepository
import com.jonrysimbolon.testskillmovie.repository.review.ReviewRepositoryImpl
import com.jonrysimbolon.testskillmovie.repository.trailer.TrailerRepository
import com.jonrysimbolon.testskillmovie.repository.trailer.TrailerRepositoryImpl
import com.jonrysimbolon.testskillmovie.utils.dialog.CustomDialog
import com.jonrysimbolon.testskillmovie.utils.dialog.ui.Failure
import com.jonrysimbolon.testskillmovie.utils.dialog.ui.Loading
import com.jonrysimbolon.testskillmovie.viewmodel.CategoryViewModel
import com.jonrysimbolon.testskillmovie.viewmodel.DetailMovieViewModel
import com.jonrysimbolon.testskillmovie.viewmodel.MovieViewModel
import com.jonrysimbolon.testskillmovie.viewmodel.TrailerViewModel
import com.jonrysimbolon.testskillmovie.viewmodel.UserReviewViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val authorization = "Authorization"

private val loggingInterceptor = with(HttpLoggingInterceptor()) {
    if (BuildConfig.DEBUG)
        setLevel(HttpLoggingInterceptor.Level.BODY)
    else
        setLevel(HttpLoggingInterceptor.Level.NONE)
}

private val client = with(OkHttpClient.Builder()) {
    addInterceptor { chain ->
        val request = chain.request().newBuilder().addHeader(
            authorization, BuildConfig.Authorization
        ).build()
        chain.proceed(request)
    }
    addInterceptor(loggingInterceptor)
    build()
}

fun retrofit(url: String): Retrofit = with(Retrofit.Builder()) {
    baseUrl(url)
    addConverterFactory(GsonConverterFactory.create())
    client(client)
    build()
}

fun remoteModule(url: String) = module {
    single { retrofit(url).create(ApiService::class.java) }
}

val gsonModule = module {
    single { Gson() }
}

val glideModule = module {
    single { Glide.with(androidContext()).setDefaultRequestOptions(get()) }
    single { RequestOptions() }
}

val repositoryModule = module {
    single<CategoryRepository> {
        CategoryRepositoryImpl(
            get(),
        )
    }
    single<MovieRepository> {
        MovieRepositoryImpl(
            get(),
        )
    }
    single<DetailMovieRepository>{
        DetailMovieRepositoryImpl(
            get(),
        )
    }
    single<TrailerRepository>{
        TrailerRepositoryImpl(
            get(),
        )
    }
    single<ReviewRepository>{
        ReviewRepositoryImpl(
            get(),
        )
    }
}

val dialogModule = module {
    single<CustomDialog> {
        Loading()
    }
    single<CustomDialog> {
        Failure()
    }
}

val adapterModule = module {
    single {
        CategoryAdapter()
    }
    single {
        MovieAdapter(get())
    }
    single {
        TrailerAdapter()
    }
    single {
        UserReviewAdapter()
    }
}

val viewModelModule = module {
    viewModelOf(::CategoryViewModel)
    viewModelOf(::MovieViewModel)
    viewModelOf(::DetailMovieViewModel)
    viewModelOf(::TrailerViewModel)
    viewModelOf(::UserReviewViewModel)
}