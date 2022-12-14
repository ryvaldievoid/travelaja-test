package com.atech.android.base.util

import android.content.Context
import android.webkit.URLUtil
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File


object GlideHelper {
    fun showThumbnail(url: String, imageView: ImageView, context: Context) {
        val options = RequestOptions().centerCrop()

        val requestBuilder = Glide.with(context)
            .load(url)

        requestBuilder
            .apply(options)
            .into(imageView)
    }

    fun showBackDrop(url: String, imageView: ImageView, context: Context) {
        val options = RequestOptions()

        val requestBuilder = Glide.with(context)
            .load(url)

        requestBuilder
            .apply(options)
            .into(imageView)
    }

    fun showThumbnailWithPlaceHolder(url: String, imageView: ImageView, context: Context, sourcePlaceHolder: Int) {
        val options = RequestOptions().centerCrop()

        val requestBuilder = Glide.with(context)
            .load(
                if (URLUtil.isValidUrl(url)) {
                    url
                } else {
                    File(url)
                }
            )

        requestBuilder
            .apply(options)
            .error(sourcePlaceHolder)
            .placeholder(sourcePlaceHolder)
            .into(imageView)
    }
}