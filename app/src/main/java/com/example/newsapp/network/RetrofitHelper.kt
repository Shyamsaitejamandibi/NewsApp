package com.example.newsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    private const val baseUrl ="https://newsapi.org/v2/"
    const val API_KEY =
//        "ca0660f10bd14e2fa177c632b920f5cc"
          "f88d8f60dfa24e7d86c260fdb214e1d6"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsService: NewsService by lazy { retrofit.create(NewsService::class.java) }
}