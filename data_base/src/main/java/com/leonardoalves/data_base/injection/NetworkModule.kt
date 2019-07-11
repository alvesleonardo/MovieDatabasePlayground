package com.leonardoalves.data_base.injection

import okhttp3.OkHttpClient
import org.koin.dsl.module

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

val networkModule = module {
    single { createOkHttpClient() }
}