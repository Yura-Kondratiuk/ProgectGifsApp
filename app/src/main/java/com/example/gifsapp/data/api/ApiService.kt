package com.example.gifsapp.data.api

import com.example.gifsapp.gifsItem.GifModelDomain
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/gifs/search")
    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("lang") lang: String,
    ): GifModelDomain

    @GET("v1/gifs/trending")
    suspend fun getRandomGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): GifModelDomain
}