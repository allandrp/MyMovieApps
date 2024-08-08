package com.alland.mymovieapps

import android.app.Application
import com.alland.mymovieapps.core.di.CoreComponent
import com.alland.mymovieapps.core.di.DaggerCoreComponent
import com.alland.mymovieapps.di.AppComponent
import com.alland.mymovieapps.di.DaggerAppComponent

class MyApplication: Application(){
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

}