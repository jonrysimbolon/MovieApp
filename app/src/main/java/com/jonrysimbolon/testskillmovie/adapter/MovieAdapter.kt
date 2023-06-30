package com.jonrysimbolon.testskillmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.databinding.ItemMovieBinding
import com.jonrysimbolon.testskillmovie.utils.setImageUrl

class MovieAdapter constructor(
    private val glide: RequestManager,
    var onClickItem: ((ViewHolder, MovieModel) -> Unit)? = null
):PagingDataAdapter<MovieModel, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieModel) {
            binding.apply {
                tvItemName.text = item.title
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)

        setImageUrl(
            glide,
            item?.posterPath.toString(),
            holder.binding.ivItemPhoto
        )

        holder.itemView.setOnClickListener {
            onClickItem?.let { onClickItem ->
                getItem(position)?.let { movieModel ->
                    onClickItem(holder, movieModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}