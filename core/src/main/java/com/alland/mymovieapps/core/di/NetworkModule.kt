package com.alland.mymovieapps.core.di

import com.alland.mymovieapps.core.BuildConfig
import com.alland.mymovieapps.core.data.remote.MovieApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideMovieApiService(): MovieApiService {
        val interceptor = Interceptor { chain ->
            val req = chain.request()
            val reqHead =
                req.newBuilder().addHeader("Authorization", "Bearer ${BuildConfig.TOKEN_API}")
                    .build()
            chain.proceed(reqHead)
        }

        val httpClient = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client =
            OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor(httpClient)
                .build()

        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.MOVIE_PATH)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()

        return retrofit.create(MovieApiService::class.java)
    }
}