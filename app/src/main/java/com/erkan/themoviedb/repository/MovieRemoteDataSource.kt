package com.erkan.themoviedb.repository

import android.content.SharedPreferences
import com.erkan.themoviedb.service.MovieApiService
import com.erkan.themoviedb.service.Resource
import com.erkan.themoviedb.service.model.CategoryList
import com.erkan.themoviedb.service.model.MovieList
import com.erkan.themoviedb.util.Constants
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieRemoteDataSource(
    val providerApiService: MovieApiService,
    val providerSharedPreferences: SharedPreferences
) {

    fun getMovieCategory(): Observable<Resource<CategoryList>> {

        val language = providerSharedPreferences.getString(Constants.APP_LANGUAGE, "")
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())

            providerApiService.getMovieCategories(language).subscribeOn(Schedulers.io())
                .subscribe({
                    emitter.onNext(Resource.success(it))
                    emitter.onComplete()
                }, {
                    emitter.onNext(Resource.error(it.message ?: ""))
                    emitter.onComplete()
                })


        }

    }

    fun getMovieList(with_genres:Int): Observable<Resource<MovieList>> {
        val language = providerSharedPreferences.getString(Constants.APP_LANGUAGE, "")
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())
            providerApiService.getMovieList(with_genres,language).subscribeOn(Schedulers.io())
                .subscribe({
                    emitter.onNext(Resource.success(it))
                    emitter.onComplete()
                }, {
                    emitter.onNext(Resource.error(it.message ?: ""))
                    emitter.onComplete()
                })


        }

    }
}