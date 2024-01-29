package com.example.newsapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.viewModels.NewsArticleViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var newsArticleViewModel: NewsArticleViewModel
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        newsArticleViewModel = NewsArticleViewModel()


        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val usersDisplayAdapter = NewsArticleRecyclerAdapter(this)

        val headerStateAdapter = LoaderAdapter()
        val footerStateAdapter = LoaderAdapter()

        usersDisplayAdapter.addLoadStateListener { combinedLoadStates ->
            headerStateAdapter.loadState = combinedLoadStates.refresh
            footerStateAdapter.loadState = combinedLoadStates.append
        }

        dataBinding.newsHeadlineRecyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = ConcatAdapter(headerStateAdapter, usersDisplayAdapter, footerStateAdapter)
        }

        newsArticleViewModel.fetchedNewsArticles.observe(this){ pagingData ->
            usersDisplayAdapter.submitData(lifecycle, pagingData)
        }

    }
}