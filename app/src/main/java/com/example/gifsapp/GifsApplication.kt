package com.example.gifsapp

import android.app.Application
import com.example.gifsapp.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GifsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainModule)
            androidContext(this@GifsApplication)
        }
    }
}