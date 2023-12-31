package com.jonrysimbolon.testskillmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.base.adapter.BasePagingDataAdapter
import com.jonrysimbolon.testskillmovie.R
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel
import com.jonrysimbolon.testskillmovie.databinding.ItemUserReviewBinding
import com.jonrysimbolon.testskillmovie.utils.withDateLongFormat

class UserReviewAdapter
    : BasePagingDataAdapter<String, UserReviewModel, UserReviewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserReviewModel) {
            binding.apply {
                item.apply {
                    authorTv.text = author
                    with(this@ViewHolder.itemView.context) {
                        createAtTv.text =
                            getString(R.string.createAt).plus(createdAt.withDateLongFormat())
                        updateAtTv.text =
                            getString(R.string.updateAt).plus(updatedAt.withDateLongFormat())
                    }
                    contentTv.text = content
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
}