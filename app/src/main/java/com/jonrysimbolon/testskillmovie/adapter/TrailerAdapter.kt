package com.jonrysimbolon.testskillmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.base.adapter.BaseRecyclerViewAdapter
import com.jonrysimbolon.testskillmovie.data.remote.model.VideoModel
import com.jonrysimbolon.testskillmovie.databinding.ItemTrailerBinding
import com.jonrysimbolon.testskillmovie.utils.startSeconds
import com.jonrysimbolon.testskillmovie.utils.withDateLongFormat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class TrailerAdapter : BaseRecyclerViewAdapter<TrailerAdapter.ViewHolder, String, VideoModel>() {

    class ViewHolder(val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoModel) {
            binding.apply {
                videoNameTv.text = item.name
                publishAtTv.text = item.publishedAt.withDateLongFormat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = oldList[position]
        holder.bind(item)

        holder.binding.videoYp.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.apply {
                    loadVideo(item.key, startSeconds)
                    pause()
                }
            }
        })
    }
}