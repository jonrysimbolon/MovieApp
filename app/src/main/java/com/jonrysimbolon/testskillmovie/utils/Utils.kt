package com.jonrysimbolon.testskillmovie.utils

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.jonrysimbolon.testskillmovie.R
import com.jonrysimbolon.testskillmovie.data.remote.model.ErrorModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun responseGsonPattern(gson: Gson, json: String?): ErrorModel =
    gson.fromJson(json, ErrorModel::class.java)

fun String.withDateSimpleFormat(): String {
    val inputFormat = SimpleDateFormat(dateSimpleFormatFromServer, Locale.getDefault())
    val outputFormat =
        DateFormat.getDateInstance(DateFormat.MEDIUM)
    val date: Date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)
}
fun String.withDateLongFormat(): String {
    val inputFormat = SimpleDateFormat(dateLongFormatFromServer, Locale.getDefault())
    val outputFormat =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault())
    val date: Date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)
}

fun setImageUrl(glide: RequestManager, url: String, imageView: ImageView) {
    try {
        glide
            .load(image_path.plus(url))
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.notfound)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }catch (ge: GlideException){
        ge.printStackTrace()
    }
}

fun setImageUrlWithRadius(glide: RequestManager, url: String, radius: Int, imageView: ImageView) {
    glide
        .load(image_path.plus(url))
        .placeholder(R.drawable.ic_launcher_foreground)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
        .error(R.drawable.notfound)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}