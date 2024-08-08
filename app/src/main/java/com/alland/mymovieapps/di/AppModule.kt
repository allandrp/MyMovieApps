package com.alland.mymovieapps.di

import com.alland.mymovieapps.core.domain.MovieInteractor
import com.alland.mymovieapps.core.domain.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}