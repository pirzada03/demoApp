package com.example.demoapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetroModule {


    val baseURL="https://api.themoviedb.org/3/"
    @Singleton
    @Provides
    fun getRetroServiceInterface(retrofit: Retrofit):MoviesApiInterface{
        return retrofit.create(MoviesApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun getRetroFitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}