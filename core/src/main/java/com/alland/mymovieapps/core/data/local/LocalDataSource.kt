package com.alland.mymovieapps.core.data.local

import android.util.Log
import com.alland.mymovieapps.core.data.local.room.MovieDao
import com.alland.mymovieapps.core.data.local.room.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    fun getFavouriteMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavouriteMovies()
    }

    suspend fun insertMovie(listMovie: List<MovieEntity>){
        return movieDao.insert(listMovie)
    }

    fun isMovieFavourite(id: Int): Flow<Boolean>{
        return movieDao.isMovieFavourite(id)
    }

    suspend fun updateMovie(movie: MovieEntity){
        Log.d("update_local_source", movie.toString())
        return movieDao.update(movie)
    }

}