package com.leonardoalves.data.movieDetail

import android.content.Context
import com.leonardoalves.data.utils.NetworkCheck
import com.leonardoalves.data.utils.onDefaultSchedulers
import com.leonardoalves.data_base.MovieDetailMapper
import com.leonardoalves.data_base.MovieDetailService
import com.leonardoalves.data_cache.MovieCacheMapper
import com.leonardoalves.data_cache.MovieDao
import com.leonardoalves.domain.detail.FetchMovieDetail
import com.leonardoalves.domain.detail.MovieDetail
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieDetailRepository(
    private val movieDetailInfrastructure: MovieDetailService,
    private val movieDao: MovieDao,
    private val context: Context
) : FetchMovieDetail {
    override fun getMovieDetail(id: Long): Observable<MovieDetail> =
        if (NetworkCheck.hasNetwork(context)) {
            movieDetailInfrastructure.getMovie(id)
                .doOnNext {
                    movieDao.insert(MovieCacheMapper.responseToCache(it))
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                }.map { MovieDetailMapper(it) }
        } else {
            movieDao.get(id)
                .map { MovieCacheMapper.cacheToMovieDetail(it) }
                .onDefaultSchedulers()
        }
}