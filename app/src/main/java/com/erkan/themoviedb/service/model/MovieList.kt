package com.erkan.themoviedb.service.model

data class MovieList(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>?
)