package com.leonardoalves.testmoviedatabase

import com.leonardoalves.testmoviedatabase.module.presentationModule
import com.leonardoalves.testmoviedatabase.module.remoteDataSourceModule
import com.leonardoalves.testmoviedatabase.module.repositoryModule
import org.koin.core.context.loadKoinModules

object Modules {
    fun init() = loadKoinModules(listOf(
        remoteDataSourceModule, repositoryModule, presentationModule
    ))
}