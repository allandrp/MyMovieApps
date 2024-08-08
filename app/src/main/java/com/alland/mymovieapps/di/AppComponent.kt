package com.alland.mymovieapps.di

import com.alland.mymovieapps.core.di.CoreComponent
import com.alland.mymovieapps.ui.MainActivity
import com.alland.mymovieapps.ui.home.HomeFragment
import com.alland.mymovieapps.ui.upcoming.UpcomingFragment
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
    fun inject(homeFragment: HomeFragment)
    fun inject(upcomingFragment: UpcomingFragment)

}