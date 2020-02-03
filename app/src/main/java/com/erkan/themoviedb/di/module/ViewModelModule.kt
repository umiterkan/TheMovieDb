package com.erkan.themoviedb.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erkan.themoviedb.di.ViewModelFactory
import com.erkan.themoviedb.di.ViewModelKey
import com.erkan.themoviedb.viewmodel.CategoryMainViewModel
import com.erkan.themoviedb.viewmodel.MainViewModel
import com.erkan.themoviedb.viewmodel.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun movieListViewModel(viewModel: MovieListViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CategoryMainViewModel::class)
    internal abstract fun categoryMainViewModel(viewModel: CategoryMainViewModel): ViewModel

}