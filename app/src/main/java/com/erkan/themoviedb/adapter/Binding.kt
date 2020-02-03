package com.erkan.themoviedb.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.erkan.themoviedb.BuildConfig
import com.squareup.picasso.Picasso

class Binding {
    companion object {
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, url: String?) {
            url?.let {
                Picasso.get().load(BuildConfig.BASE_URL_IMAGE + url).into(view)
            }
        }
    }
}