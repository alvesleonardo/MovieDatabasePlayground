package com.leonardoalves.data.api

import com.leonardoalves.data_base.models.MovieDetailResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseV3Api{
    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Long,
                       @Query("api_key") apiKey: String = API_KEY
    ) : Observable<MovieDetailResponse>
}