package com.leonardoalves.data.injection

import com.leonardoalves.data.ExecutionErrorHandler
import com.leonardoalves.data.MovieDataBaseInfrastructure
import com.leonardoalves.data.MovieDatabaseApi
import com.leonardoalves.data.MovieShowroomRepository
import com.leonardoalves.domain.showroom.FetchMoviesList
import com.leonardoalves.services_base.RetrofitBuilder
import org.koin.dsl.module

const val SERVER_URL = "https://api.themoviedb.org/4/"

val dataMovieDatabaseModule = module {
    single { RetrofitBuilder.createWebService<MovieDatabaseApi>(get(), SERVER_URL) }
    single { MovieDataBaseInfrastructure(get(), ExecutionErrorHandler()) }
}
val repositoryModule = module {
    single<FetchMoviesList> { MovieShowroomRepository(get()) }
}