package com.alland.mymovieapps.core.di

import com.alland.mymovieapps.core.data.MovieRepository
import com.alland.mymovieapps.core.domain.IMovieRepository
import com.alland.mymovieapps.core.domain.MovieInteractor
import com.alland.mymovieapps.core.domain.MovieUseCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository
}