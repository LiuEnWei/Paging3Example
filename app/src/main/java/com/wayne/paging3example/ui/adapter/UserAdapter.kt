package com.wayne.paging3example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.wayne.paging3example.R
import com.wayne.paging3example.model.vo.User
import com.wayne.paging3example.ui.adapter.viewholder.UserViewHolder

class UserAdapter(private val onUserClickListener: OnUserClickListener): PagingDataAdapter<User, UserViewHolder>(diffCallback)  {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.onBind(user, onUserClickListener)
        }
    }


}