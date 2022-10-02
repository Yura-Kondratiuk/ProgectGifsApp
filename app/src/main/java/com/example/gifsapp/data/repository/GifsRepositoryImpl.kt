package com.example.gifsapp.data.repository

import android.content.Context
import com.example.gifsapp.data.api.ApiService
import com.example.gifsapp.db.AppDatabase
import com.example.gifsapp.db.Id
import com.example.gifsapp.gifsItem.GifModelDomain

class GifsRepositoryImpl(private val apiService: ApiService, val context: Context) : GifsRepository {

    private val db = AppDatabase.getDatabase(context)
    private val apiKey = "PdMSZaNHuK0NKOj8Iw47h3SBVsA0VGit"
    private val language = "en"

    override suspend fun getGifs(query: String): GifModelDomain {
        return apiService.getGifs(apiKey, query, LIMIT, language)
    }

    override suspend fun getRandomGifs(): GifModelDomain {
        return apiService.getRandomGifs(apiKey, LIMIT)
    }

    override suspend fun saveRemovedId(id: String) {
        db.dao().insertAll(Id(id))
    }

    override suspend fun getRemovedIds(): List<String> {
        return db.dao().getAll().map {
            it.id
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}