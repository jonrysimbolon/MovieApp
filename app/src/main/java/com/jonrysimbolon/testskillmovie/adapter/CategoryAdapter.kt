package com.jonrysimbolon.testskillmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.base.adapter.BaseRecyclerViewAdapter
import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel
import com.jonrysimbolon.testskillmovie.databinding.ItemCategoryBinding

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CategoryModel) {
        binding.apply {
            categoryName.text = item.name
        }
    }
}

class CategoryAdapter :
    BaseRecyclerViewAdapter<CategoryViewHolder, Int, CategoryModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = oldList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener { view ->
            onClickItem?.let { it(view, item) }
        }
    }
}