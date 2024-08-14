package com.alland.mymovieapps.core.di

import com.alland.mymovieapps.core.BuildConfig
import com.alland.mymovieapps.core.data.remote.MovieApiService
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideMovieApiService(): MovieApiService {
        val hostName = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/k1Hdw5sdSn5kh/gemLVSQD/P4i4IBQEY1tW4WNxh9XM=")
            .add(hostName, "sha256/18tkPyr2nckv4fgo0dhAkaUtJ2hu2831xlO2SKhq8dg=")
            .build()

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
                .certificatePinner(certificatePinner)
                .build()

        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.MOVIE_PATH)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()

        return retrofit.create(MovieApiService::class.java)
    }
}