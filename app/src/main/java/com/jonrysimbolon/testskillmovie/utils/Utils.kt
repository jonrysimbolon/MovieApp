package com.jonrysimbolon.testskillmovie.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jonrysimbolon.testskillmovie.R
import com.jonrysimbolon.testskillmovie.data.remote.model.ErrorModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val EMPTY_CHAR = ""
private val gson: Gson =
    GsonBuilder()
        .setFieldNamingPolicy(
            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
        .create()
private val requestOption: RequestOptions = RequestOptions()

fun responseGsonPattern(json: String?): ErrorModel =
    gson.fromJson(json, ErrorModel::class.java)

fun String.withDateSimpleFormat(): String {
    val inputFormat = SimpleDateFormat(dateSimpleFormatFromServer, Locale.getDefault())
    val outputFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
    val date: Date = inputFormat.parse(this) ?: return EMPTY_CHAR
    return outputFormat.format(date)
}

fun String.withDateLongFormat(): String {
    val inputFormat = SimpleDateFormat(dateLongFormatFromServer, Locale.getDefault())
    val outputFormat =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault())
    val date: Date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)
}

private fun glideConfig(context: Context): RequestManager =
    Glide.with(context)
        .setDefaultRequestOptions(requestOption)

fun setImageUrl(
    context: Context,
    url: String,
    imageView: ImageView,
    radius: Int = 1
) {
    glideConfig(context)
        .load(image_path.plus(url))
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.notfound)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}