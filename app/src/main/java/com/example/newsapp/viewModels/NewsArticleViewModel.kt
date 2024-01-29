package com.example.newsapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.respositories.NewsArticleRepository

class NewsArticleViewModel: ViewModel() {
    private var newsArticleRepo: NewsArticleRepository = NewsArticleRepository()
    val fetchedNewsArticles = newsArticleRepo.fetchNewsArticles().cachedIn(viewModelScope)

}