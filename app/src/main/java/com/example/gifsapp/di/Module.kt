package com.example.gifsapp.di

import com.example.gifsapp.data.api.ApiService
import com.example.gifsapp.data.repository.GifsRepository
import com.example.gifsapp.data.repository.GifsRepositoryImpl
import com.example.gifsapp.viewModels.GifsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val mainModule = module {
    single<GifsRepository> {
        GifsRepositoryImpl(get(),get())
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    viewModel {
        GifsViewModel(get())
    }
}