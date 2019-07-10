package com.leonardoalves.data

import com.leonardoalves.domain.showroom.FetchMoviesList
import com.leonardoalves.domain.showroom.MovieShowRoom
import com.leonardoalves.services_base.MovieDatabaseService
import com.leonardoalves.services_base.MovieShowRoomMapper
import io.reactivex.Observable

class MovieShowroomRepository(private val movieDataBaseInfrastructure: MovieDatabaseService) : FetchMoviesList {
    override fun getList(type: FetchMoviesList.Type, page: Int): Observable<List<MovieShowRoom>> = when(type){
        FetchMoviesList.Type.POPULAR -> movieDataBaseInfrastructure.getPopularMovies(page)
        FetchMoviesList.Type.TOP_RATED -> movieDataBaseInfrastructure.getTopRatedMovies(page)
        FetchMoviesList.Type.UPCOMING -> movieDataBaseInfrastructure.getUpcomingMovies(page)
    }.map { MovieShowRoomMapper(it) }
}