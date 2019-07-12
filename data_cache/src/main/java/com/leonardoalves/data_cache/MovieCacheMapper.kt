package com.leonardoalves.data_cache

import com.leonardoalves.data_base.MovieShowRoomMapper.IMAGE_URL
import com.leonardoalves.data_base.MovieShowRoomMapper.PLACEHOLDER_URL
import com.leonardoalves.data_base.models.MovieDetailResponse
import com.leonardoalves.data_base.models.MovieListResponse
import com.leonardoalves.domain.detail.MovieDetail
import com.leonardoalves.domain.showroom.MovieShowRoom

object MovieCacheMapper {

    fun cacheToMovieDetail(cache: MovieCache) = with(cache){
        MovieDetail(
            id = uid,
            adult = adult,
            homepage = homepage,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            poster = poster,
            voteAverage = voteAverage
        )
    }

    fun responseToCache(response: MovieDetailResponse) = with(response){
        MovieCache(
            uid = id,
            adult = adult ?: false,
            homepage = homepage,
            title = title ?: "",
            originalTitle = originalTitle ?: "",
            overview = overview ?: "",
            poster = if(!posterPath.isNullOrBlank()) IMAGE_URL+posterPath else null,
            voteAverage = voteAverage
        )
    }

    fun responseToCacheTopRated(response: MovieListResponse, page: Int) = with(response){
        this.results.orEmpty().map {
            MovieCacheTopRated(
                uid = it.id,
                adult = it.adult ?: false,
                title = it.title ?: "",
                originalTitle = it.originalTitle ?: "",
                overview = it.overview ?: "",
                poster = if (!it.posterPath.isNullOrBlank()) IMAGE_URL + it.posterPath else null,
                voteAverage = it.voteAverage,
                homepage = null,
                position = this.results.orEmpty().indexOf(it) * page
            )
        }
    }


    fun responseToCachePopular(response: MovieListResponse, page: Int) = with(response){
        this.results.orEmpty().map {
            MovieCachePopular(
                uid = it.id,
                adult = it.adult ?: false,
                title = it.title ?: "",
                originalTitle = it.originalTitle ?: "",
                overview = it.overview ?: "",
                poster = if (!it.posterPath.isNullOrBlank()) IMAGE_URL + it.posterPath else null,
                voteAverage = it.voteAverage,
                homepage = null,
                position = this.results.orEmpty().indexOf(it) * page
            )
        }
    }

    fun responseToCacheUpcoming(response: MovieListResponse, page: Int) = with(response){
        this.results.orEmpty().map {
            MovieCacheUpcoming(
                uid = it.id,
                adult = it.adult ?: false,
                title = it.title ?: "",
                originalTitle = it.originalTitle ?: "",
                overview = it.overview ?: "",
                poster = if (!it.posterPath.isNullOrBlank()) IMAGE_URL + it.posterPath else null,
                voteAverage = it.voteAverage,
                homepage = null,
                position = this.results.orEmpty().indexOf(it) * page
            )
        }
    }

    fun cacheToMovieShowroom(cache: MovieCacheUpcoming) = with(cache){
        MovieShowRoom(
            id = uid,
            poster = poster ?: PLACEHOLDER_URL,
            localizedTitle = title,
            originalTitle = originalTitle,
            overview = overview
        )
    }

    fun cacheToMovieShowroom(cache: MovieCachePopular) = with(cache){
        MovieShowRoom(
            id = uid,
            poster = poster ?: PLACEHOLDER_URL,
            localizedTitle = title,
            originalTitle = originalTitle,
            overview = overview
        )
    }

    fun cacheToMovieShowroom(cache: MovieCacheTopRated) = with(cache){
        MovieShowRoom(
            id = uid,
            poster = poster ?: PLACEHOLDER_URL,
            localizedTitle = title,
            originalTitle = originalTitle,
            overview = overview
        )
    }
}