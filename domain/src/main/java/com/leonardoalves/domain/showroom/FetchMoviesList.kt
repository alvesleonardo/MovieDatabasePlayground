package com.leonardoalves.domain.showroom

import io.reactivex.Observable
import java.io.Serializable

interface FetchMoviesList {
    enum class Type : Serializable { UPCOMING, TOP_RATED, POPULAR }
    fun getList(type: Type, page: Int) : Observable<List<MovieShowRoom>>
}