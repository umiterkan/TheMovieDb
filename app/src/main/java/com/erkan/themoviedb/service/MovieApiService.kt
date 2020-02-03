package com.erkan.themoviedb.service

import com.erkan.themoviedb.service.model.CategoryList
import com.erkan.themoviedb.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("genre/movie/list?api_key=3bb3e67969473d0cb4a48a0dd61af747&language=en-US")
    fun getMovieCategories(@Query("language") language: String?): Call<CategoryList>

    @GET("discover/movie?api_key=3bb3e67969473d0cb4a48a0dd61af747&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMovieList(@Query("with_genres") with_genres: Int?, @Query("language") language: String?): Call<MovieList>


}