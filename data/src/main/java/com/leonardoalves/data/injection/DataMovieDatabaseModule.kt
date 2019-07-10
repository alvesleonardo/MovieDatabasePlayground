package com.leonardoalves.data.injection

import com.leonardoalves.data.ExecutionErrorHandler
import com.leonardoalves.data.MovieDataBaseInfrastructure
import com.leonardoalves.data.MovieDatabaseApi
import com.leonardoalves.data.MovieShowroomRepository
import com.leonardoalves.domain.showroom.FetchMoviesList
import com.leonardoalves.services_base.MovieDatabaseService
import com.leonardoalves.services_base.RetrofitBuilder
import com.leonardoalves.services_base.injection.networkModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

const val SERVER_URL = "https://api.themoviedb.org/4/"

private val dataMovieDatabaseModule = module {
    single { RetrofitBuilder.createWebService<MovieDatabaseApi>(get(), SERVER_URL) }
    single <MovieDatabaseService> { MovieDataBaseInfrastructure(get(), ExecutionErrorHandler()) }
}

private val repositoriesModule = module {
    single<FetchMoviesList> { MovieShowroomRepository(get()) }
}
val dataModule = listOf(networkModule, dataMovieDatabaseModule, repositoriesModule)