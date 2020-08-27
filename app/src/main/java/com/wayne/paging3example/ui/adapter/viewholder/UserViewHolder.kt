package com.wayne.paging3example.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wayne.paging3example.R
import com.wayne.paging3example.model.vo.User
import com.wayne.paging3example.ui.adapter.OnUserClickListener
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imgAvatar = itemView.imgAvatar
    private val textLogin = itemView.textLogin
    private val textStaff = itemView.textStaff

    fun onBind(user: User, onUserClickListener: OnUserClickListener) {
        itemView.setOnClickListener {
            onUserClickListener.onClick(user)
        }
        Glide.with(imgAvatar)
            .load(user.avatarUrl)
            .placeholder(R.drawable.ic_avatar_placeholder)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(imgAvatar)
        textLogin.text = user.login
        textStaff.visibility = if (user.isSiteAdmin) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}