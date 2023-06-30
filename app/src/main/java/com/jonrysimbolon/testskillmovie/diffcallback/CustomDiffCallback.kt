package com.jonrysimbolon.testskillmovie.diffcallback

import androidx.recyclerview.widget.DiffUtil

class CustomDiffCallback<T>(
    private val mOldList: List<T>,
    private val mNewList: List<T>,
    private val itemComparator: (T, T) -> Boolean,
    private val contentComparator: (T, T) -> Boolean
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = mOldList.size

    override fun getNewListSize(): Int = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        itemComparator(mOldList[oldItemPosition], mNewList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        contentComparator(mOldList[oldItemPosition], mNewList[newItemPosition])
}