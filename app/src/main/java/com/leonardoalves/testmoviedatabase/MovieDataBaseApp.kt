package com.leonardoalves.testmoviedatabase

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieDataBaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startDependencies()
        Stetho.initializeWithDefaults(this)
    }

    fun startDependencies(){
        startKoin {
            androidContext(this@MovieDataBaseApp)
        }
        Modules.init()
    }
}