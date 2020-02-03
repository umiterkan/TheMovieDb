package com.erkan.themoviedb.ui.movies

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.erkan.themoviedb.R
import com.erkan.themoviedb.databinding.ActivityMovieDetailBinding
import com.erkan.themoviedb.ui.base.BaseActivity

class MovieDetailActivity : BaseActivity() {


    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        intent.extras?.let {
            val movie = MovieDetailActivityArgs.fromBundle(it).argMovie
            binding.data = movie
        }
    }


}