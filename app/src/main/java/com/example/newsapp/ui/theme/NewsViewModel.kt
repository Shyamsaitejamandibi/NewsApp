package com.example.newsapp.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel:ViewModel() {

    private val articlesFlow = MutableStateFlow<List<Article>>(emptyList())

    fun getNews(){
        viewModelScope.launch(Dispatchers.IO) {
            val newsService = RetrofitHelper.newsService

            try {
                val response = newsService.fetchHeadlines(
                    country = "in",
                    category = "technology",
                    apiKey = RetrofitHelper.API_KEY
                )
                articlesFlow.emit(response.articles)
            }catch (e:Exception){
                Log.e("NewsViewModel",e.toString())
            }
        }
    }
    fun getArticlesFlow(): StateFlow<List<Article>> {
        return articlesFlow.asStateFlow()
    }
}