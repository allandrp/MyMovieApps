package com.alland.mymovieapps.core.di

import android.content.Context
import androidx.room.Room
import com.alland.mymovieapps.core.data.local.room.MovieDao
import com.alland.mymovieapps.core.data.local.room.MovieDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao{
        return database.movieDao()
    }
}