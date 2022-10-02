package com.example.gifsapp.data.repository

import com.example.gifsapp.gifsItem.GifModelDomain


interface GifsRepository {

    suspend fun getGifs(query: String = ""): GifModelDomain

    suspend fun getRandomGifs(): GifModelDomain

    suspend fun saveRemovedId(id: String)

    suspend fun getRemovedIds(): List<String>

}