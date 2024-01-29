package com.example.newsapp.respositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.newsapp.api.NewsApi
import com.example.newsapp.helpers.Constants
import com.example.newsapp.models.Article
import com.example.newsapp.paging.NewsArticlePagingDataSource

class NewsArticleRepository {
    private var newsApi: NewsApi = NewsApi()

    fun fetchNewsArticles(): LiveData<PagingData<Article>>{
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ARTICLE_FETCH_COUNT,
                maxSize = Constants.MAX_ARTICLES_IN_MEMORY,
            ),
            pagingSourceFactory = {
                NewsArticlePagingDataSource(newsApi)
            }
            , initialKey = 1
        ).liveData
    }
}