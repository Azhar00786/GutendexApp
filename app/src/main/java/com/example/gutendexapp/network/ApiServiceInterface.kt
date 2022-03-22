package com.example.gutendexapp.network

import com.example.gutendexapp.model.Books
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("books/?mime_type=image%2Fjpeg")
    fun getBooksByCategory(@Query("topic") category: String): Call<Books>

    @GET("books/?mime_type=image%2Fjpeg")
    fun getBooksBySearch(
        @Query("topic") category: String,
        @Query("search") key: String
    ): Call<Books>
}