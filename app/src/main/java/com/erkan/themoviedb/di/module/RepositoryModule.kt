package com.erkan.themoviedb.di.module

import com.erkan.themoviedb.repository.MovieLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(): MovieLocalDataSource {
        return MovieLocalDataSource()
    }

}