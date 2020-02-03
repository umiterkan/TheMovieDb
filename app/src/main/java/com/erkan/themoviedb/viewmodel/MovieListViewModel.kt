package com.erkan.themoviedb.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erkan.themoviedb.repository.MovieLocalDataSource
import com.erkan.themoviedb.service.model.Movie
import com.erkan.themoviedb.service.model.MovieList
import com.erkan.themoviedb.service.MovieApiService
import com.erkan.themoviedb.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val providerapiService: MovieApiService,
    private val providerSharedPreferences: SharedPreferences,
    private val provideMovieLocalDataSource: MovieLocalDataSource
) :
    ViewModel() {

    private var data: MutableLiveData<List<Movie>>? = null
    var error = MutableLiveData<Boolean>()

    fun getMoviewList(categoryId: Int): MutableLiveData<List<Movie>> {
        if (data == null) {
            data = MutableLiveData<List<Movie>>()
            requestMovieList(categoryId)
        }
        return data as MutableLiveData<List<Movie>>
    }

    fun requestMovieList(categoryId: Int) {

        provideMovieLocalDataSource.selectMovieList(categoryId)?.let {
            data?.postValue(it)
        } ?: run {
            providerapiService.getMovieList(
                categoryId,
                providerSharedPreferences.getString(Constants.APP_LANGUAGE, "")
            ).enqueue(object : Callback<MovieList> {
                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    error.postValue(true)
                    Log.d("msg", "MovieListViewModel onFailure")
                }

                override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                    Log.d("msg", "MovieListViewModel onResponse $categoryId")
                    response.body()?.results?.let {
                        provideMovieLocalDataSource.addMovieList(categoryId, it)
                        data?.postValue(it)
                    }
                }
            })
        }


    }


}