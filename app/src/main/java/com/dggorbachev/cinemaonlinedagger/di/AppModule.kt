package com.dggorbachev.cinemaonlinedagger.di

import com.dggorbachev.cinemaonlinedagger.base.common.Constants.BASE_URL
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MovieApi
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRemoteSource
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRepo
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRepoImpl
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.MoviesInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    // logging api data
    val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
}