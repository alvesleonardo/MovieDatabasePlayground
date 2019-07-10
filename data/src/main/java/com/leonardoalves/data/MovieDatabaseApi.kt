package com.leonardoalves.data

import android.annotation.SuppressLint
import com.leonardoalves.services_base.models.MovieListResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

const val API_KEY = "7bceb0b1f7044212ad30ef524c01b8d4"

interface MovieDatabaseApi {

    @GET("discover/movie") //At least 5k votes to be significant
    fun getPopular(
        @Query("page") page: Int, @Query("api_key") apikey: String = API_KEY, @Query("sort_by") sortBy: String = "popularity.desc", @Query(
            "vote_count.gte"
        ) moreThanVotes: Int = 5000
    ): Observable<MovieListResponse>

    @GET("discover/movie")
    fun getUpcoming(
        @Query("page") page: Int,
        @SuppressLint("SimpleDateFormat") @Query("primary_release_date.gte") after: String = SimpleDateFormat("yyyy-MM-dd").format(
            Date()
        ),
        @Query("api_key") apikey: String = API_KEY
    ): Observable<MovieListResponse>

    @GET("discover/movie?sort_by=vote_average.desc")
    fun getTopRated(@Query("page") page: Int, @Query("api_key") apikey: String = API_KEY): Observable<MovieListResponse>

//    @GET("find/")
//    fun getMovie(@Path("external_id") id: Long, @Query("api_key") apikey: String = API_KEY): Flowable<com.leonardoalves.services_base.data.Movie>
}