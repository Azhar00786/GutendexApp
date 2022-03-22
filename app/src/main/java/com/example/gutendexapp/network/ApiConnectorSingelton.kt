package com.example.gutendexapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConnectorSingelton {
    private const val BASE_URL = "https://gutendex.com/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}

