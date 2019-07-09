package com.leonardoalves.services_base

import com.leonardoalves.domain.MovieShowRoom
import com.leonardoalves.services_base.MovieShowRoomMapper.IMAGE_URL
import com.leonardoalves.services_base.models.MovieListResponse
import com.leonardoalves.services_base.models.MovieResponse
import org.junit.Test

import org.junit.Assert.*

class MovieShowRoomMapperTests {
    @Test
    fun mapServerResponseToMovieShowRoomModel() {
        val response = MovieListResponse(
            results = listOf(
                MovieResponse(
                    id = 100,
                    posterPath = "/image.jpg",
                    title = "The sleeping beauty",
                    originalTitle = "The sleeping beauty",
                    overview = null
                )
            )
        )
        val expected = listOf(
            MovieShowRoom(
                id = 100,
                poster = "$IMAGE_URL/image.jpg",
                originalTitle = "The sleeping beauty",
                localizedTitle = "The sleeping beauty",
                overview = ""
            )
        )
        assertEquals(MovieShowRoomMapper(response), expected)
    }
}