package com.example.newsapp.network

import com.example.newsapp.data.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun fetchHeadlines(
        @Query("country") country:String,
        @Query("category") category: String,
        @Query("apiKey") apiKey:String
    ): News
}
