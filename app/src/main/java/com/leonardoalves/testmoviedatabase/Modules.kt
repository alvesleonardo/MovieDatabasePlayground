package com.leonardoalves.testmoviedatabase

import com.leonardoalves.feature_movies_showcase.injection.moviesShowroomModule
import org.koin.core.context.loadKoinModules

object Modules {
    fun init() = loadKoinModules(
        moviesShowroomModule
    )
}