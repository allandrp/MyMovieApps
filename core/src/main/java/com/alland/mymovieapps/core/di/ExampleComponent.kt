package com.alland.mymovieapps.core.di

import dagger.Component

@Component(modules = [DatabaseModule::class])
interface ExampleComponent {

    @Component.Factory
    interface Factory{
        fun factory(): ExampleComponent
    }
}