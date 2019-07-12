package com.leonardoalves.data.api

import android.annotation.SuppressLint
import com.leonardoalves.data_base.models.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

const val API_KEY = "7bceb0b1f7044212ad30ef524c01b8d4"

interface MovieDatabaseV4Api {

    @GET("discover/movie")
    fun getPopular(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Observable<MovieListResponse>

    @GET("discover/movie")
    fun getUpcoming(
        @Query("page") page: Int,
        @SuppressLint("SimpleDateFormat") @Query("primary_release_date.gte") after: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
        @Query("api_key") apiKey: String = API_KEY
    ): Observable<MovieListResponse>

    @GET("discover/movie")
    fun getTopRated(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "voteAverage.desc",
        @Query("vote_count.gte") moreThanVotes: Int = 5000, //At least 5k votes to be significant
        @Query("api_key") apiKey: String = API_KEY
    ): Observable<MovieListResponse>

//    @GET("find/")
//    fun getMovie(@Path("external_id") id: Long, @Query("api_key") apiKey: String = API_KEY): Observable<MovieResponse>
}