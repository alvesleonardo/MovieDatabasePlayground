package com.leonardoalves.data.movieShowroom

import android.content.Context
import com.leonardoalves.data.utils.NetworkCheck
import com.leonardoalves.data.utils.onDefaultSchedulers
import com.leonardoalves.data_base.MovieShowRoomMapper
import com.leonardoalves.data_base.MovieShowroomService
import com.leonardoalves.data_cache.MovieCacheMapper
import com.leonardoalves.data_cache.MovieDao
import com.leonardoalves.data_cache.MovieShowroomDao
import com.leonardoalves.domain.showroom.FetchMoviesList
import com.leonardoalves.domain.showroom.MovieShowRoom
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieShowroomRepository(
    private val movieDataBaseInfrastructure: MovieShowroomService,
    private val movieDao: MovieShowroomDao,
    private val context: Context
) : FetchMoviesList {
    override fun getList(type: FetchMoviesList.Type, page: Int): Observable<List<MovieShowRoom>> =
        if (NetworkCheck.hasNetwork(context)) {
            when (type) {
                FetchMoviesList.Type.POPULAR -> movieDataBaseInfrastructure.getPopularMovies(page)
                FetchMoviesList.Type.TOP_RATED -> movieDataBaseInfrastructure.getTopRatedMovies(page)
                FetchMoviesList.Type.UPCOMING -> movieDataBaseInfrastructure.getUpcomingMovies(page)
            }
                .doOnNext {
                    when (type) {
                        FetchMoviesList.Type.POPULAR -> movieDao.insertAllPopulars(MovieCacheMapper.responseToCachePopular(it, page))
                        FetchMoviesList.Type.TOP_RATED -> movieDao.insertAllTopRated(MovieCacheMapper.responseToCacheTopRated(it, page))
                        FetchMoviesList.Type.UPCOMING -> movieDao.insertAllUpcoming(MovieCacheMapper.responseToCacheUpcoming(it, page))
                    }.observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                }
                .map { MovieShowRoomMapper(it) }
        } else {
            if(page > 1){
                Observable.just(listOf())
            } else {
                when (type) {
                    FetchMoviesList.Type.POPULAR -> movieDao.getPopulars().map { list ->
                        list.map { MovieCacheMapper.cacheToMovieShowroom(it) }
                    }
                    FetchMoviesList.Type.TOP_RATED -> movieDao.getTopRated().map { list ->
                        list.map { MovieCacheMapper.cacheToMovieShowroom(it) }
                    }
                    FetchMoviesList.Type.UPCOMING -> movieDao.getUpcoming().map { list ->
                        list.map { MovieCacheMapper.cacheToMovieShowroom(it) }
                    }
                }.onDefaultSchedulers()
            }
        }
}