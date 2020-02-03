package com.erkan.themoviedb.service.model

import java.io.Serializable

data class Movie(
    val popularity: Double,
    val vote_count: Double,
    val poster_path: String,
    val id: Int,
    val backdrop_path: String,
    val original_title: String,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String
):Serializable