package com.jonrysimbolon.base.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jonrysimbolon.base.model.BaseModel

abstract class BaseRecyclerViewAdapter<VH : ViewHolder, T, I: BaseModel<T>> : RecyclerView.Adapter<VH>() {

    protected val oldList: ArrayList<I> = ArrayList()

    var onClickItem: ((View, I) -> Unit)? = null

    fun updateData(newList: List<I>) {
        val diffCallback = DiffCallback(
            oldList,
            newList,
            { oldItem, newItem ->
                oldItem.id == newItem.id
            }
        ) { oldItem, newItem ->
            oldItem == newItem
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.oldList.clear()
        this.oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = oldList.size
}

class DiffCallback<T>(
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