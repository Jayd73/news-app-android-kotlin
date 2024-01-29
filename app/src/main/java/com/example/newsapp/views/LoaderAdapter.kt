package com.example.newsapp.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.LoaderItemBinding

class LoaderAdapter: LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {
    private lateinit var dataBinding: LoaderItemBinding

    inner class LoaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(loadState: LoadState){
            dataBinding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        dataBinding = LoaderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return LoaderViewHolder(dataBinding.root)
    }
}