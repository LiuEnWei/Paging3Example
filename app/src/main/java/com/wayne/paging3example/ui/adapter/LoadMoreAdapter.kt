package com.wayne.paging3example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.wayne.paging3example.R
import com.wayne.paging3example.ui.adapter.viewholder.LoadMoreViewHolder

class LoadMoreAdapter(private val onReloadListener: OnReloadListener) : LoadStateAdapter<LoadMoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadMoreViewHolder {
        return LoadMoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false))
    }

    override fun onBindViewHolder(holder: LoadMoreViewHolder, loadState: LoadState) {
        holder.onBind(loadState, onReloadListener)
    }
}