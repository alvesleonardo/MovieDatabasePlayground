package com.leonardoalves.data.movieShowroom

import com.leonardoalves.domain.showroom.FetchMoviesList
import com.leonardoalves.domain.showroom.MovieShowRoom
import com.leonardoalves.data_base.MovieShowroomService
import com.leonardoalves.data_base.MovieShowRoomMapper
import com.leonardoalves.data_cache.MovieCacheMapper
import com.leonardoalves.data_cache.MovieDao
import io.reactivex.Observable

class MovieShowroomRepository(private val movieDataBaseInfrastructure: MovieShowroomService, private val movieDao: MovieDao) : FetchMoviesList {
    override fun getList(type: FetchMoviesList.Type, page: Int): Observable<List<MovieShowRoom>> = when(type){
        FetchMoviesList.Type.POPULAR -> movieDataBaseInfrastructure.getPopularMovies(page)
        FetchMoviesList.Type.TOP_RATED -> movieDataBaseInfrastructure.getTopRatedMovies(page)
        FetchMoviesList.Type.UPCOMING -> movieDataBaseInfrastructure.getUpcomingMovies(page)
    }.doOnNext { movieDao.insertAll(MovieCacheMapper.responseToCache(it)) }.map { MovieShowRoomMapper(it) }
}