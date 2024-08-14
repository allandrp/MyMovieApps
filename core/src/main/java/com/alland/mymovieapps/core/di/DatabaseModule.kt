package com.alland.mymovieapps.core.di

import android.content.Context
import androidx.room.Room
import com.alland.mymovieapps.core.data.local.room.MovieDao
import com.alland.mymovieapps.core.data.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): MovieDatabase {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("password".toCharArray())
        val factory = SupportFactory(passPhrase)
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}