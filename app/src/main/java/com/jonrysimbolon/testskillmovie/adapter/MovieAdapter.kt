package com.jonrysimbolon.testskillmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.base.adapter.BasePagingDataAdapter
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.databinding.ItemMovieBinding
import com.jonrysimbolon.testskillmovie.utils.setImageUrl

class MovieAdapter constructor(
    var onClickItem: ((ViewHolder, MovieModel) -> Unit)? = null
) : BasePagingDataAdapter<Int, MovieModel, MovieAdapter.ViewHolder>() {

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
            holder.binding.ivItemPhoto.context,
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
}