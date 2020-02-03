package com.erkan.themoviedb.di.module

import com.erkan.themoviedb.ui.category.CategoryMainFragment
import com.erkan.themoviedb.ui.movies.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment


    @ContributesAndroidInjector
    abstract fun contributeCategoryMainFragment(): CategoryMainFragment

}