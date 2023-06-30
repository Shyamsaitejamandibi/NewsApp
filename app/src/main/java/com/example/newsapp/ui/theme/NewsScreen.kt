package com.example.newsapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.data.Article


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NewsViewModel){

    viewModel.getNews()
    val article by viewModel.getArticlesFlow().collectAsState()

    Scaffold(
        topBar = { TitleBar() }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding){
            items(article){item->
                NewsItem(item)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(){
    CenterAlignedTopAppBar(title = { Text(text="NewsApp") })
}

@Composable
fun NewsItem(article:Article){
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Column{
            AsyncImage(model = article.urlToImage, contentDescription = null)
            Text(text = article.title?:"Loading...", modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = article.description?:"Loading...", maxLines = 4, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), fontSize = 16.sp)
        }
    }
}

@Preview
@Composable
fun HMP(){
    HomeScreen(viewModel = NewsViewModel())
}