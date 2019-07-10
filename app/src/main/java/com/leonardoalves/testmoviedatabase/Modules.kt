package com.leonardoalves.testmoviedatabase

import com.leonardoalves.testmoviedatabase.module.presentationModule
import org.koin.core.context.loadKoinModules

object Modules {
    fun init() = loadKoinModules(listOf(
        com.leonardoalves.data.remoteDataSourceModule, com.leonardoalves.data.repositoryModule, presentationModule
    ))
}