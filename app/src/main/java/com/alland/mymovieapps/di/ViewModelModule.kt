package com.alland.mymovieapps.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alland.mymovieapps.core.ui.ViewModelFactory
import com.alland.mymovieapps.ui.detail.DetailViewModel
import com.alland.mymovieapps.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideDetailViewModel(upcomingViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}