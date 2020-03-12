package com.kendhammer.john.countries.util

import android.content.Context
import android.media.Image
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kendhammer.john.countries.R

fun getProgressDrawable(context: (Context)): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide
        .with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}