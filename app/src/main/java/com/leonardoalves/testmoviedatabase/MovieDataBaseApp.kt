package com.leonardoalves.testmoviedatabase

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieDataBaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startDependencies()
    }

    fun startDependencies(){
        startKoin {
            androidContext(this@MovieDataBaseApp)
        }
        Modules.init()
    }
}