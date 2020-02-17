package com.erkan.themoviedb.repository

import com.erkan.themoviedb.service.model.Movie

class MovieLocalDataSource {

    private val localMovieList: HashMap<Int, List<Movie>> = HashMap()

    fun addMovieList(categoryId: Int, list: List<Movie>?) {
        list?.let {
            localMovieList.put(categoryId, it)
        }
    }

    fun selectMovieList(categoryId: Int): List<Movie>? {
        return localMovieList.get(categoryId)
    }

}