package com.erkan.themoviedb.ui.movies

import com.erkan.themoviedb.service.model.Movie

interface MovieItemListenerInterface {
    fun itemMovieListener(movie: Movie)
}