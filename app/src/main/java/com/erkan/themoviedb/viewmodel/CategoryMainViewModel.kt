package com.erkan.themoviedb.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erkan.themoviedb.repository.MovieRemoteDataSource
import com.erkan.themoviedb.service.model.CategoryList
import com.erkan.themoviedb.service.MovieApiService
import com.erkan.themoviedb.service.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryMainViewModel @Inject constructor(
    val providerApiService: MovieApiService,
    val providerSharedPreferences: SharedPreferences
) : ViewModel() {

    private var movieRemoteDataSource = MovieRemoteDataSource(providerApiService, providerSharedPreferences)
    private var dataMovieCategories: MutableLiveData<CategoryList>? = null
    var error = MutableLiveData<Boolean>()



    fun getCategoryList(): MutableLiveData<CategoryList> {
        if (dataMovieCategories == null) {
            dataMovieCategories = MutableLiveData()
            requestMovieCategories()
        }
        return dataMovieCategories as MutableLiveData<CategoryList>
    }

    fun requestMovieCategories() {
        movieRemoteDataSource
            .getMovieCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            when (it.status) {
                Status.SUCCESS -> {
                    dataMovieCategories?.postValue(it.data)
                }
                Status.ERROR -> {
                    error.postValue(true)
                }
                Status.LOADING -> {
                }
            }
        }
    }

}