package com.leonardoalves.data_base

import com.leonardoalves.data_base.models.MovieDetailResponse
import io.reactivex.Observable

interface MovieDetailService {
    fun getMovie(id:Long):Observable<MovieDetailResponse>
}