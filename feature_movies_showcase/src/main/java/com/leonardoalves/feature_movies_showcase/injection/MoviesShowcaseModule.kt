package com.leonardoalves.feature_movies_showcase.injection

import com.leonardoalves.data.injection.dataModule
import com.leonardoalves.domain.di.iteratorsModule

val moviesShowroomModule = mutableListOf(presentationModule, iteratorsModule).apply { addAll(dataModule) }