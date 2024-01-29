package com.example.newsapp.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsHeadlineItemBinding
import com.example.newsapp.helpers.Constants
import com.example.newsapp.models.Article
import com.squareup.picasso.Picasso


class NewsArticleRecyclerAdapter(private val context: Context) :
    PagingDataAdapter<Article, NewsArticleRecyclerAdapter.NewsArticleViewHolder>(
        NewsArticleComparator
    ) {
    private lateinit var dataBinding: NewsHeadlineItemBinding

    inner class NewsArticleViewHolder(val dataBinding: NewsHeadlineItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root)

    object NewsArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        dataBinding = NewsHeadlineItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsArticleViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article: Article? = getItem(position)
        if (article != null) {
            holder.dataBinding.headlineTextView.text = convertToDisplayReadyText(
                article.title,
                Constants.MAX_HEADLINE_SIZE
            )
            holder.dataBinding.descriptionTextView.text =
                if (article.description != null) convertToDisplayReadyText(
                    article.description,
                    Constants.MAX_DESCRIPTION_SIZE
                )
                else "No description available"
            if (article.urlToImage != null) {
                Picasso.get().load(article.urlToImage).into(holder.dataBinding.articlePhotoImgView)
            } else {
                holder.dataBinding.articlePhotoImgView.setImageResource(R.drawable.news_default)
            }

            holder.dataBinding.openLinkBtn.setOnClickListener {
                if(article.url != null) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    context.startActivity(browserIntent)
                }else{
                    holder.dataBinding.openLinkBtn.isEnabled = false
                }
            }
        }
    }


    private fun convertToDisplayReadyText(text: String, maxTextLen: Int): String {
        if (text.length > maxTextLen) {
            return text.take(maxTextLen) + "..."
        }
        return text
    }

}