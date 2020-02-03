package com.erkan.themoviedb.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erkan.themoviedb.service.model.CategoryList
import com.erkan.themoviedb.service.MovieApiService
import com.erkan.themoviedb.util.Constants.Companion.APP_LANGUAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CategoryMainViewModel @Inject constructor(
    val providerApiService: MovieApiService,
    val providerSharedPreferences: SharedPreferences
) : ViewModel() {

    private var dataMovieCategories: MutableLiveData<CategoryList>? = null
    var error = MutableLiveData<Boolean>()

    fun getCategoryList(): MutableLiveData<CategoryList> {
        if (dataMovieCategories == null) {
            dataMovieCategories = MutableLiveData<CategoryList>()
            requestMovieCategories()
        }
        return dataMovieCategories as MutableLiveData<CategoryList>
    }

    fun requestMovieCategories() {
        providerApiService.getMovieCategories(providerSharedPreferences.getString(APP_LANGUAGE, ""))
            .enqueue(object : Callback<CategoryList> {
                override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    error.postValue(true)
                    Log.d("msg", "CategoryMainViewModel onFailure")
                }

                override fun onResponse(
                    call: Call<CategoryList>,
                    response: Response<CategoryList>
                ) {
                    Log.d("msg", "CategoryMainViewModel onResponse")
                    dataMovieCategories?.postValue(response.body())
                }
            })

    }

}