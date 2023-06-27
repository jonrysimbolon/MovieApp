package com.jonrysimbolon.testskillmovie.application

import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.jonrysimbolon.testskillmovie.BuildConfig
import com.jonrysimbolon.testskillmovie.data.local.Database
import com.jonrysimbolon.testskillmovie.data.remote.ApiService
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepository
import com.jonrysimbolon.testskillmovie.repository.category.CategoryRepositoryImpl
import com.jonrysimbolon.testskillmovie.ui.splash.SplashViewModel
import com.jonrysimbolon.testskillmovie.utils.movie_database
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

val localModule = module {
    single {
        Room.databaseBuilder(androidContext(), Database::class.java, movie_database)
            .fallbackToDestructiveMigration().build()
    }
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
            get()
        )
    }
}

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
}