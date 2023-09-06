package com.jonrysimbolon.base.adapter

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.base.model.BaseModel

abstract class BasePagingDataAdapter<I, T : BaseModel<I>, VH : RecyclerView.ViewHolder>
    : PagingDataAdapter<T, VH>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return newItem.id.toString() == oldItem.id.toString()
    }
})