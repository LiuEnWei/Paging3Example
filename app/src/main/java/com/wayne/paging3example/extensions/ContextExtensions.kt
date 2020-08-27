package com.wayne.paging3example.extensions

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun Context.getRecyclerViewDivider(@DrawableRes drawableId: Int): RecyclerView.ItemDecoration {
    val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
    ContextCompat.getDrawable(this, drawableId)?.let {
        itemDecoration.setDrawable(it)
    }
    return itemDecoration
}