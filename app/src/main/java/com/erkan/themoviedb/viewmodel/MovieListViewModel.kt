package com.erkan.themoviedb.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erkan.themoviedb.repository.MovieLocalDataSource
import com.erkan.themoviedb.repository.MovieRemoteDataSource
import com.erkan.themoviedb.service.model.Movie
import com.erkan.themoviedb.service.MovieApiService
import com.erkan.themoviedb.service.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val providerapiService: MovieApiService,
    private val providerSharedPreferences: SharedPreferences,
    private val provideMovieLocalDataSource: MovieLocalDataSource
) :
    ViewModel() {

    private var movieRemoteDataSource = MovieRemoteDataSource(providerapiService, providerSharedPreferences)
    private var data: MutableLiveData<List<Movie>>? = null
    var error = MutableLiveData<Boolean>()

    fun getMoviewList(categoryId: Int): MutableLiveData<List<Movie>> {
        if (data == null) {
            data = MutableLiveData()
            requestMovieList(categoryId)
        }
        return data as MutableLiveData<List<Movie>>
    }

    fun requestMovieList(categoryId: Int) {

        provideMovieLocalDataSource.selectMovieList(categoryId)?.let {
            data?.postValue(it)
        } ?: run {
            movieRemoteDataSource.getMovieList(categoryId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    when (it.status) {
                        Status.LOADING -> {
                        }
                        Status.ERROR -> {
                            error.postValue(true)
                        }
                        Status.SUCCESS -> {
                            provideMovieLocalDataSource.addMovieList(categoryId, it.data?.results)
                            data?.postValue(it.data?.results)
                        }
                    }
                }
        }


    }


}