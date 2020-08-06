package com.renovavision.footballapp.data

import com.renovavision.footballapp.data.api.Api
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(
        @Named("cacheDir") httpCacheDir: File?,
        @Named("apiUrl") apiUrl: String
    ) = Retrofit.Builder()
        .baseUrl(apiUrl)
        .callFactory(OkHttpClient.Builder().apply {
            httpCacheDir?.let { cache(Cache(it, (1024 * 1024 * 100))) }
        }.build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java);
}