package com.jonrysimbolon.testskillmovie.application

import android.app.Application
import com.jonrysimbolon.testskillmovie.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            fragmentFactory()
            modules(
                gsonModule,
                remoteModule(BuildConfig.Base_Url),
                glideModule,
                repositoryModule,
                dialogModule,
                adapterModule,
                viewModelModule,
            )
        }
    }
}