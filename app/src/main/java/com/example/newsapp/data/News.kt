package com.example.newsapp.data

data class News (
    val status:String,
    val totalResult: Int,
    val articles: List<Article>
)