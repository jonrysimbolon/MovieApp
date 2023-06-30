package com.jonrysimbolon.testskillmovie.utils

import androidx.recyclerview.widget.ListUpdateCallback
import com.jonrysimbolon.testskillmovie.data.remote.model.CategoryModel
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.data.remote.model.UserReviewModel
import com.jonrysimbolon.testskillmovie.data.remote.model.VideoModel

object DataDummy {
    fun generateDummyCategoryResponse(): List<CategoryModel> {
        val items: MutableList<CategoryModel> = arrayListOf()
        for (i in 0..100) {
            val quote = CategoryModel(
                i,
                "name$i"
            )
            items.add(quote)
        }
        return items
    }

    fun generateDummyMovieResponse(): List<MovieModel> {
        val items: MutableList<MovieModel> = arrayListOf()
        for (i in 0..100) {
            val movie = MovieModel(
                i,
                "poster$i",
                "name$i"
            )
            items.add(movie)
        }
        return items
    }

    fun generateDummyUserReviewResponse(): List<UserReviewModel>{
        val items: MutableList<UserReviewModel> = arrayListOf()
        for (i in 0..100) {
            val movie = UserReviewModel(
                "$i",
                "author$i",
                "content$i",
                "create$i",
                "update$i",
            )
            items.add(movie)
        }
        return items
    }

    fun generateDummyVideoResponse(): List<VideoModel>{
        val items: MutableList<VideoModel> = arrayListOf()
        for (i in 0..100) {
            val video = VideoModel(
                "$i",
                "key$i",
                "name$i",
                "publish$i"
            )
            items.add(video)
        }
        return items
    }
}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}