package com.jonrysimbolon.testskillmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel
import com.jonrysimbolon.testskillmovie.databinding.ItemCategoryBinding
import com.jonrysimbolon.testskillmovie.diffcallback.CustomDiffCallback

class CategoryAdapter constructor(
    var onClickItem: ((View, CategoryModel) -> Unit)? = null
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val oldList: ArrayList<CategoryModel> = ArrayList()

    fun updateData(newList: List<CategoryModel>) {
        val diffCallback = CustomDiffCallback(
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

    class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel) {
            binding.apply {
                categoryName.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = oldList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = oldList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener { view ->
            onClickItem?.let { categoryModel ->
                categoryModel(view, item)
            }
        }
    }

}