package com.wayne.paging3example.ui.adapter.viewholder

import android.util.Log
import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.wayne.paging3example.ui.adapter.OnReloadListener
import kotlinx.android.synthetic.main.item_load_more.view.*

class LoadMoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val progress = itemView.progress
    private val btnReload = itemView.btnReload

    fun onBind(loadState: LoadState, onReloadListener: OnReloadListener) {
        Log.e("LoadMoreViewHolder", "loadState : $loadState")

        when (loadState) {
            is LoadState.Loading -> {
                progress.visibility = View.VISIBLE
                btnReload.visibility = View.GONE
            }

            is LoadState.Error -> {
                progress.visibility = View.GONE
                btnReload.visibility = View.VISIBLE
                btnReload.setOnClickListener {
                    onReloadListener.onReloadClick()
                }
            }
        }
    }
}