package com.erkan.themoviedb.repository

import com.erkan.themoviedb.service.model.Movie

class MovieLocalDataSource {

    private val localMovieList = HashMap<Int, List<Movie>>()

    fun addMovieList(categoryId: Int, list: List<Movie>) {
        localMovieList.put(categoryId, list)
    }

    fun selectMovieList(categoryId: Int): List<Movie>? {
        return localMovieList.get(categoryId)
    }

}