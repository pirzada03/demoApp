package com.example.demoapp.di

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.demoapp.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.Header

interface MoviesApiInterface {

    @GET("movie/top_rated")
    suspend fun getTop10Movies(
        @Header("Authorization") token: String,
        @Query("language") language: String ,
        @Query("page") page: Int = 1
    ): Response<MoviesResponse>
}