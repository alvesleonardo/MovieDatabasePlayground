package com.leonardoalves.testmoviedatabase.view.movieList

import com.leonardoalves.feature_common.custom.ViewModel

class MovieViewModel(
    val id: Long,
    val picture: String,
    val title: String,
    val description: String
): ViewModel