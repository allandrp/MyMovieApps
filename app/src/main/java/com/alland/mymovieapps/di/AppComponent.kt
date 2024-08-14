package com.alland.mymovieapps.di

import com.alland.mymovieapps.core.di.CoreComponent
import com.alland.mymovieapps.core.domain.MovieUseCase
import com.alland.mymovieapps.ui.detail.DetailActivity
import com.alland.mymovieapps.ui.home.MainActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
    fun provideMovieUseCase(): MovieUseCase
}