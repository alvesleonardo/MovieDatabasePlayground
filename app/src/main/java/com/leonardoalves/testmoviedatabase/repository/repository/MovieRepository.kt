package com.leonardoalves.testmoviedatabase.repository.repository

import com.leonardoalves.services_base.data.Movie
import com.leonardoalves.testmoviedatabase.repository.api.Api
import com.leonardoalves.testmoviedatabase.repository.utils.onDefaultSchedulers
import io.reactivex.Flowable

class MovieRepository (private val api: Api){
    fun getPopularMovies(page: Int) = api.getPopular(page)
        .onDefaultSchedulers()
        .map { it.results.orEmpty() }
        .map { list -> list.filterNotNull() }

    fun getUpcomingMovies(page: Int): Flowable<List<com.leonardoalves.services_base.data.Movie>> {
        return api.getUpcoming(page)
            .onDefaultSchedulers()
            .map { it.results.orEmpty() }
            .map { list -> list.filterNotNull() }
    }

    fun getTopRatedMovies(page: Int): Flowable<List<com.leonardoalves.services_base.data.Movie>> {
        return api.getTopRated(page)
            .onDefaultSchedulers()
            .map { it.results.orEmpty() }
            .map { list -> list.filterNotNull() }
    }
}