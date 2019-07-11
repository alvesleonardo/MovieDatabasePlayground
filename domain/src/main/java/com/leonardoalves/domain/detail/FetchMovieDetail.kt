package com.leonardoalves.domain.detail

import io.reactivex.Observable

interface FetchMovieDetail {
    fun getMovieDetail(id: Long) : Observable<MovieDetail>
}