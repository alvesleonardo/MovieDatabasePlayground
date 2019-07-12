package com.leonardoalves.data.injection

import com.leonardoalves.data.api.MovieDatabaseV3Api
import com.leonardoalves.data.api.MovieDatabaseV4Api
import com.leonardoalves.data.movieDetail.MovieDetailInfrastructure
import com.leonardoalves.data.movieDetail.MovieDetailRepository
import com.leonardoalves.data.movieShowroom.MovieShowroomInfrastructure
import com.leonardoalves.data.movieShowroom.MovieShowroomRepository
import com.leonardoalves.data.utils.ExecutionErrorHandler
import com.leonardoalves.data_base.MovieDetailService
import com.leonardoalves.domain.showroom.FetchMoviesList
import com.leonardoalves.data_base.MovieShowroomService
import com.leonardoalves.data_base.RetrofitBuilder
import com.leonardoalves.data_base.injection.networkModule
import com.leonardoalves.data_cache.di.cacheModule
import com.leonardoalves.domain.detail.FetchMovieDetail
import org.koin.dsl.module

const val SERVER_URL_V4 = "https://api.themoviedb.org/4/"
const val SERVER_URL_V3 = "https://api.themoviedb.org/3/"

private val dataMovieDatabaseModule = module {
    single { RetrofitBuilder.createWebService<MovieDatabaseV4Api>(get(), SERVER_URL_V4) }
    single { RetrofitBuilder.createWebService<MovieDatabaseV3Api>(get(), SERVER_URL_V3)}
    single <MovieShowroomService> {
        MovieShowroomInfrastructure(
            get(),
            ExecutionErrorHandler()
        )
    }
    single <MovieDetailService> { MovieDetailInfrastructure(get(), ExecutionErrorHandler())}
}

private val repositoriesModule = module {
    single<FetchMoviesList> { MovieShowroomRepository(get(), get(), get()) }
    single<FetchMovieDetail> { MovieDetailRepository(get(), get(), get()) }
}
val dataModule = listOf(networkModule, dataMovieDatabaseModule, repositoriesModule, cacheModule)