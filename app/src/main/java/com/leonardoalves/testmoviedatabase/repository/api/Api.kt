package com.leonardoalves.testmoviedatabase.repository.api

import com.leonardoalves.testmoviedatabase.data.Movie
import com.leonardoalves.testmoviedatabase.data.MovieList
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

const val API_KEY = "7bceb0b1f7044212ad30ef524c01b8d4"

interface Api {

    @GET("list/")
    fun getList(@Query("page") page: Int, @Query("api_key") apikey: String = API_KEY): Flowable<MovieList>

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopular(@Query("page") page: Int, @Query("api_key") apikey: String = API_KEY): Flowable<MovieList>

    @GET("discover/movie")
    fun getUpcoming(@Query("page") page: Int,
                    @Query("primary_release_date.gte") after: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
                    @Query("api_key") apikey: String = API_KEY): Flowable<MovieList>

    @GET("discover/movie?sort_by=vote_average.desc")
    fun getTopRated(@Query("page") page: Int, @Query("api_key") apikey: String = API_KEY): Flowable<MovieList>

    @GET("find/")
    fun getMovie(@Path("external_id") id: Long, @Query("api_key") apikey: String = API_KEY): Flowable<Movie>
}