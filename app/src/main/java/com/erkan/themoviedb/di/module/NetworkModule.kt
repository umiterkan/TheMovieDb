package com.erkan.themoviedb.di.module

import com.erkan.themoviedb.BuildConfig
import com.erkan.themoviedb.service.MovieApiService
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    fun providerApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }


    @Provides
    @Singleton
    fun providerRetrofitService(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", "3bb3e67969473d0cb4a48a0dd61af747").build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build();
                return chain.proceed(request);
            }
        })

        return okHttpClient.build()
    }


}