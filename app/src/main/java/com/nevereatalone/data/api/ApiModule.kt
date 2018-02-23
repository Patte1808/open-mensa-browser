package com.nevereatalone.data.api

import com.nevereatalone.common.Consts.Companion.API_ENDPOINT
import com.nevereatalone.openmensa.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor { message ->
                Timber.tag("OkHttp").d(message)
            }
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        return clientBuilder
                .followRedirects(false)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(API_ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()


}