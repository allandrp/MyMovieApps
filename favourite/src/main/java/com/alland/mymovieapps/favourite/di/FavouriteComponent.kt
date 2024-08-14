package com.alland.mymovieapps.favourite.di

import com.alland.mymovieapps.di.AppComponent
import com.alland.mymovieapps.favourite.ui.FavouriteActivity
import dagger.Component

@Component(dependencies = [AppComponent::class])
@ActivityScope
interface FavouriteComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FavouriteComponent
    }

    fun inject(favouriteActivity: FavouriteActivity)
}