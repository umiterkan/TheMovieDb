package com.erkan.themoviedb.di

import android.app.Application
import com.erkan.themoviedb.MyApplicaton
import com.erkan.themoviedb.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, NetworkModule::class, ActivitiesModule::class, ViewModelModule::class, FragmentsModule::class,RepositoryModule::class])
interface AppComponent : AndroidInjector<MyApplicaton> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    override fun inject(application: MyApplicaton)

}