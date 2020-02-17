package com.erkan.themoviedb.service

import com.erkan.themoviedb.service.model.CategoryList
import com.erkan.themoviedb.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("genre/movie/list")
    fun getMovieCategories(@Query("language") language: String?): Call<CategoryList>

    @GET("discover/movie?sort_by=popularity.desc&page=1")
    fun getMovieList(@Query("with_genres") with_genres: Int?, @Query("language") language: String?): Call<MovieList>


}