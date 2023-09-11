package com.nekivai.github_viewer2.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

fun ImageView.loadUriWithCover(uri: String?, @DrawableRes placeHolderId: Int? = null) {
    val glideBuilder = baseLoad(uri, placeHolderId)
        .error(baseLoad(uri, placeHolderId))

    glideBuilder.into(this)
}

fun ImageView.baseLoad(
    uri: String?,
    @DrawableRes placeHolderId: Int? = null,
): RequestBuilder<Drawable> {
    var glideBuilder = Glide.with(context)
        .load(uri)
    if (placeHolderId != null) {
        glideBuilder = glideBuilder.placeholder(placeHolderId)
    }
    return glideBuilder
}