package com.leonardoalves.domain.showroom

class MovieShowroomIterator(private val movieShowroomRepository:FetchMoviesList) {

    fun getList(page: Int, type: FetchMoviesList.Type) = movieShowroomRepository.getList(type, page).map { list -> list.distinctBy { it.id } }

}