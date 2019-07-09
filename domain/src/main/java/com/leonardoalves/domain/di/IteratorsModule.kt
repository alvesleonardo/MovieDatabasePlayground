package com.leonardoalves.domain.di

import com.leonardoalves.domain.showroom.MovieShowroomIterator
import org.koin.dsl.module

val iteratorsModule = module {
    factory { MovieShowroomIterator(get()) }
}