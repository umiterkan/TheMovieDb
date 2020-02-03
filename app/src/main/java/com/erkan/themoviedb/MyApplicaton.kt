package com.erkan.themoviedb

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.app.Service
import com.erkan.themoviedb.di.AppInjector
import com.erkan.themoviedb.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject




class MyApplicaton : Application(),HasActivityInjector,HasFragmentInjector{


    @Inject
    lateinit var dispatchingAndroidServiceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        //DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun fragmentInjector(): AndroidInjector<Fragment> =fragmentInjector


}
